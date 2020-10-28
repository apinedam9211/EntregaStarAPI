package co.com.bcs.apibcs.controller.v1.depositoelectronico;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.bcs.apibcs.dto.v1.entities.NumeroAutorizacion;

import co.com.bcs.apibcs.dto.v1.entities.TiempoVidaToken;

import co.com.bcs.apibcs.dto.v1.entities.Cabecera;
import co.com.bcs.apibcs.dto.v1.entities.Credito;
import co.com.bcs.apibcs.dto.v1.entities.Debito;
import co.com.bcs.apibcs.dto.v1.entities.DepositoElectronico;
import co.com.bcs.apibcs.dto.v1.entities.DepositoID;
import co.com.bcs.apibcs.dto.v1.entities.DepositoSearchDTO;
import co.com.bcs.apibcs.dto.v1.entities.MedioManejo;
import co.com.bcs.apibcs.dto.v1.entities.Movimiento;
import co.com.bcs.apibcs.dto.v1.entities.Otc;
import co.com.bcs.apibcs.dto.v1.entities.Saldo;
import co.com.bcs.apibcs.dto.v1.entities.SolicitudDeposito;
import co.com.bcs.apibcs.exception.NotFoundException;
import co.com.bcs.apibcs.mappers.ControllerMapper;

import co.com.bcs.apibcs.services.AlcanciaAmigaService;
import co.com.bcs.apibcs.services.MedioManejoService;
import co.com.bcs.apibcs.services.OTCTokenService;
import co.com.bcs.apibcs.services.TransaccionesFinancierasService;
import co.com.bcs.apibcs.services.dto.ConsultarMovimientosDepositoRequest;
import co.com.bcs.apibcs.services.dto.ConsultarMovimientosDepositoResponse;
import co.com.bcs.apibcs.services.dto.CreacionMedioManejoRequest;
import co.com.bcs.apibcs.services.dto.ValidarDepositoRequest;
import co.com.bcs.apibcs.validators.ConstraintCelular;
import co.com.bcs.apibcs.validators.ConstraintStringAsNumber;
import co.com.bcs.apibcs.validators.ConstraintTipoId;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "v1/depositos-electronicos")
@Validated
public class DepositoElectronicoController implements IDepositoElectronicoController {

	private AlcanciaAmigaService alcanciaAmigaService;

	private OTCTokenService otcTokenService;

	private TransaccionesFinancierasService transaccionesFinancierasService;

	private MedioManejoService medioManejoService;

	private ControllerMapper controllerMapper;

	@GetMapping(path = "", produces = "application/json")
	public ResponseEntity<DepositoElectronico> validarDepositoElectronico(
			@RequestParam("tipoDocumento") @ConstraintTipoId(message = "{tipoId.noValid}") String tipoDocumento,
			@Valid Cabecera cabecera,
			@RequestParam("numeroDocumento") @ConstraintStringAsNumber(maxSize = 18, message = "{numeroDocumento.isNull}") String numeroDocumento,
			@RequestParam("correo") @NotNull(message = "{email.isNull}") @NotEmpty(message = "{email.isEmpty}") @Email(message = "{email.format}") String correo,
			@RequestParam("celular") @ConstraintCelular(message = "{celular.isNumeric}") String celular) {

		DepositoSearchDTO request = new DepositoSearchDTO();
		request.setCelular(celular);
		request.setCorreo(correo);
		request.setNumeroDocumento(numeroDocumento);
		request.setTipoDocumento(tipoDocumento);
		ValidarDepositoRequest requestService = controllerMapper.toValidarDepositoRequest(request, cabecera);
		boolean depositoExiste = alcanciaAmigaService.validarDeposito(requestService);

		if (depositoExiste) {
			DepositoElectronico deposito = DepositoElectronico.fromData(tipoDocumento, numeroDocumento);
			return new ResponseEntity<>((deposito), HttpStatus.OK);
		}

		throw new NotFoundException("Deposito Electronico no Encontrado");

	}

	@GetMapping(path = "/{id}", produces = "application/hal+json")
	public ResponseEntity<DepositoElectronico> consultarDepositoElectronico(@Valid DepositoID id,
			@Valid Cabecera cabecera,
			@RequestParam @NotNull(message = "{email.isNull}") @NotEmpty(message = "{email.isEmpty}") @Email(message = "{email.format}") String correo,
			@RequestParam @ConstraintCelular(message = "{celular.isNumeric}") String celular) {

		return validarDepositoElectronico(id.getTipoDocumento(), cabecera, id.getNumeroDocumento(), correo, celular);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "", consumes = "application/json", produces = "application/json")
	public ResponseEntity<DepositoElectronico> aperturaDepositoElectronico(
			@Valid @RequestBody SolicitudDeposito request, @Valid Cabecera cabecera) {

		alcanciaAmigaService.solicitarDeposito(controllerMapper.toSolicitarDeposito(request, cabecera));

		DepositoElectronico depositoElectronico = DepositoElectronico.fromData(request.getTipoDocumento(),
				request.getNumeroDocumento());

		return new ResponseEntity<>((depositoElectronico), HttpStatus.CREATED);
	}

	@PostMapping(path = "/{id}/debito", consumes = "application/json", produces = "application/json")
	public ResponseEntity<NumeroAutorizacion> retiroDepositoElectronico(@Valid @RequestBody Debito request,
			@Valid Cabecera cabecera, @Valid DepositoID id) {

		String numAutorizacion = transaccionesFinancierasService
				.retiro(controllerMapper.toDebitoDepositoElectronicoRequest(request, cabecera, id));
		NumeroAutorizacion num = NumeroAutorizacion.builder().numeroAutorizacion(numAutorizacion).build();
		return new ResponseEntity<>(num, HttpStatus.OK);
	}

	@PostMapping(path = "/{id}/credito", consumes = "application/json", produces = "application/json")
	public ResponseEntity<NumeroAutorizacion> consignacionDepositoElectronico(@Valid @RequestBody Credito request,
			@Valid Cabecera cabecera, @Valid DepositoID id) {

		String numAutorizacion = transaccionesFinancierasService
				.credito(controllerMapper.toCreditoDepositoElectronicoRequest(request, cabecera, id));
		NumeroAutorizacion num = NumeroAutorizacion.builder().numeroAutorizacion(numAutorizacion).build();
		return new ResponseEntity<>(num, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}/saldos", produces = "application/json")

	public ResponseEntity<Saldo> consultarSaldoDepositoElectronico(@Valid DepositoID id, @Valid Cabecera cabecera,
			@RequestParam("celular") @ConstraintCelular(message = "{celular.isNumeric}") String celular) {

		String saldo = alcanciaAmigaService
				.consultarSaldo(controllerMapper.toConsultarSaldoDepositoRequest(cabecera, id, celular));

		return new ResponseEntity<>(Saldo.builder().descripcion("Saldo Disponible").valor(saldo).build(),
				HttpStatus.OK);

	}

	@GetMapping(path = "/{id}/movimientos", produces = "application/json")
	public ResponseEntity<List<Movimiento>> consultarMovimientosDepositoElectronico(
			@RequestParam("pagina") Integer pagina, @Valid Cabecera cabecera, @Valid DepositoID id,
			@RequestParam("celular") @ConstraintCelular(message = "{celular.isNumeric}") String celular) {

		ConsultarMovimientosDepositoRequest request = controllerMapper.toConsultarMovimientosDepositoRequest(pagina,
				cabecera, id, celular);

		ConsultarMovimientosDepositoResponse result = alcanciaAmigaService.consultarMovimientos(request);

		List<Movimiento> movimientos = result.getMovimientos().stream()
				.map(x -> new Movimiento(x.getFecha(), x.getDescripcion(), x.getValor())).collect(Collectors.toList());

		return new ResponseEntity<>((movimientos), HttpStatus.OK);

	}

	@PostMapping(path = "/{id}/otc", consumes = "application/json", produces = "application/json")
	public ResponseEntity<TiempoVidaToken> obtenerOtcRetiro(@Valid @RequestBody Otc request, @Valid Cabecera cabecera,
			@Valid DepositoID id) {

		String reponse = otcTokenService.solicitarToken(controllerMapper.toOTCRetiroRequest(cabecera, request, id));
		return new ResponseEntity<>(new TiempoVidaToken((reponse)), HttpStatus.OK);
	}

	@Override
	@PostMapping(path = "/{id}/medio-manejo", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Void> solicitarMedioDeManejo(@Valid MedioManejo request, @Valid Cabecera cabecera,
			@Valid DepositoID id) {

		CreacionMedioManejoRequest creacionMedioManejo = controllerMapper.toCreacionMedioManejoRequest(request, cabecera , id);
		medioManejoService.crearMedioManejo(creacionMedioManejo);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}