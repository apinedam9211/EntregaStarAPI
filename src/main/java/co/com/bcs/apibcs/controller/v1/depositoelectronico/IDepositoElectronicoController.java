package co.com.bcs.apibcs.controller.v1.depositoelectronico;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.bcs.apibcs.dto.v1.entities.NumeroAutorizacion;
import co.com.bcs.apibcs.dto.v1.entities.TiempoVidaToken;
import co.com.bcs.apibcs.validators.ConstraintCelular;
import co.com.bcs.apibcs.validators.ConstraintStringAsNumber;
import co.com.bcs.apibcs.validators.ConstraintTipoId;
import co.com.bcs.apibcs.dto.v1.entities.Cabecera;
import co.com.bcs.apibcs.dto.v1.entities.Credito;
import co.com.bcs.apibcs.dto.v1.entities.Debito;
import co.com.bcs.apibcs.dto.v1.entities.DepositoElectronico;
import co.com.bcs.apibcs.dto.v1.entities.DepositoID;
import co.com.bcs.apibcs.dto.v1.entities.MedioManejo;
import co.com.bcs.apibcs.dto.v1.entities.Movimiento;
import co.com.bcs.apibcs.dto.v1.entities.Otc;
import co.com.bcs.apibcs.dto.v1.entities.Saldo;
import co.com.bcs.apibcs.dto.v1.entities.SolicitudDeposito;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.*;

@Tag(name = "Transacciones depósito electrónico", description = "Listado de transacciones asociadas al producto depósito electrónico por medio de la aplicación StarAPI")
public interface IDepositoElectronicoController {

	@Operation(parameters = {
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "hash-message", required = false, description = "Código de autenticación de mensajes basado en Hash-Message"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-cliente-corresponsal", required = true, description = "Dirección IP del cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-servidor-corresponsal", required = true, description = "Dirección IP del servidor del corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-cliente-corresponsal", required = true, description = "Dirección MAC cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-servidor-corresponsal", required = true, description = "Dirección MAC corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "numero-referencia", required = true, description = "Número de referencia de la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "pais-origen", required = true, description = "País origen donde se realiza la transacción") }, responses = {
					@ApiResponse(responseCode = "201", description = "Apertura correcta"),
					@ApiResponse(responseCode = "400", description = "Formato de datos incorrecto") }, summary = "Transacción que permite la apertura de un depósito electrónico para un cliente por medio de un aliado del Banco Caja Social", description = "Transacción que permite la apertura de un depósito electrónico para un cliente por medio de un aliado del Banco Caja Social")
	public ResponseEntity<DepositoElectronico> aperturaDepositoElectronico(
			@Valid @RequestBody SolicitudDeposito request, @Parameter(hidden = true) @Valid Cabecera cabecera);

	@Parameters(value = {

			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "hash-message", required = false, description = "Código de autenticación de mensajes basado en Hash-Message"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-cliente-corresponsal", required = true, description = "Dirección IP del cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-servidor-corresponsal", required = true, description = "Dirección IP del servidor del corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-cliente-corresponsal", required = true, description = "Dirección MAC cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-servidor-corresponsal", required = true, description = "Dirección MAC corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "numero-referencia", required = true, description = "Número de referencia de la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "pais-origen", required = true, description = "País origen donde se realiza la transacción") })
	@Operation(summary = "Transacción que permite validar si un cliente posee depósito electrónico con el Banco Caja Social", description = "Transacción que permite validar si un cliente posee depósito electrónico con el Banco Caja Social")
	public ResponseEntity<DepositoElectronico> validarDepositoElectronico(
			@RequestParam("tipoDocumento") @ConstraintTipoId(message = "{tipoId.noValid}") @Parameter(name = "tipoDocumento", description = "Tipo de identificación del cliente asociado al depósito electrónico") String tipoDocumento,
			@Parameter(hidden = true) @Valid Cabecera cabecera,
			@RequestParam("numeroDocumento") @Parameter(name = "numeroDocumento", description = "Número de identificación del cliente asociado al depósito electrónico") @ConstraintStringAsNumber(maxSize = 18, message = "{numeroDocumento.isNull}") String numeroDocumento,
			@RequestParam("correo") @Parameter(name = "correo", description = "Correo del cliente asociado al depósito electrónico") @NotNull(message = "{email.isNull}") @NotEmpty(message = "{email.isEmpty}") @Email(message = "{email.format}") String correo,
			@RequestParam("celular") @Parameter(name = "celular", description = "Número de celular del cliente asociado al depósito electrónico") @ConstraintCelular(message = "{celular.isNumeric}") String celular);

	@Parameters(value = {

			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "hash-message", required = false, description = "Código de autenticación de mensajes basado en Hash-Message"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-cliente-corresponsal", required = true, description = "Dirección IP del cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-servidor-corresponsal", required = true, description = "Dirección IP del servidor del corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-cliente-corresponsal", required = true, description = "Dirección MAC cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-servidor-corresponsal", required = true, description = "Dirección MAC corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "numero-referencia", required = true, description = "Número de referencia de la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "pais-origen", required = true, description = "País origen donde se realiza la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH, name = "id", required = true, description = "Identificador único de transacción para depósito electrónico") })
	@Operation(summary = "Transacción que permite consultar la existencia de un depósito electrónico con el Banco Caja Social", description = "Transacción que permite consultar la existencia de un depósito electrónico con el Banco Caja Social")
	public ResponseEntity<DepositoElectronico> consultarDepositoElectronico(
			@Valid @Parameter(hidden = true) DepositoID id, @Parameter(hidden = true) @Valid Cabecera cabecera,
			@RequestParam @Parameter(name = "correo", description = "Correo al que está asociado el depósito electrónico para consultar su existencia") @NotNull(message = "{email.isNull}") @NotEmpty(message = "{email.isEmpty}") @Email(message = "{email.format}") String correo,
			@RequestParam @Parameter(name = "celular", description = "Celular al que está asociado el depósito electrónico para consultar su existencia") @ConstraintCelular(message = "{celular.isNumeric}") String celular);

	@Parameters(value = {
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "Authorization", required = true),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "hash-message", required = false, description = "Código de autenticación de mensajes basado en Hash-Message"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-cliente-corresponsal", required = true, description = "Dirección IP del cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-servidor-corresponsal", required = true, description = "Dirección IP del servidor del corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-cliente-corresponsal", required = true, description = "Dirección MAC cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-servidor-corresponsal", required = true, description = "Dirección MAC corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "numero-referencia", required = true, description = "Número de referencia de la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "pais-origen", required = true, description = "País origen donde se realiza la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH, name = "id", required = true, description = "Identificador único de transacción para depósito electrónico") })
	@Operation(summary = "Transacción que permite consultar el saldo de un depósito electrónico", description = "Transacción que permite consultar el saldo de un depósito electrónico")
	public ResponseEntity<Saldo> consultarSaldoDepositoElectronico(@Parameter(hidden = true) @Valid DepositoID id,
			@Parameter(hidden = true) @Valid Cabecera cabecera,
			@RequestParam @Parameter(name = "celular", description = "Celular al que está asociado el depósito electrónico para consultar el saldo") @ConstraintCelular(message = "{celular.isNumeric}") String celular);

	@Parameters(value = {
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "Authorization", required = true),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "hash-message", required = false, description = "Código de autenticación de mensajes basado en Hash-Message"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-cliente-corresponsal", required = true, description = "Dirección IP del cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-servidor-corresponsal", required = true, description = "Dirección IP del servidor del corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-cliente-corresponsal", required = true, description = "Dirección MAC cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-servidor-corresponsal", required = true, description = "Dirección MAC corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "numero-referencia", required = true, description = "Número de referencia de la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "pais-origen", required = true, description = "País origen donde se realiza la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH, name = "id", required = true, description = "Identificador único de transacción para depósito electrónico") })
	@Operation(summary = "Transacción que permite consultar los movimientos financieros de un depósito electrónico", description = "Transacción que permite consultar los movimientos financieros de un depósito electrónico")
	public ResponseEntity<List<Movimiento>> consultarMovimientosDepositoElectronico(
			@RequestParam("pagina") @Parameter(name = "pagina", description = "Número de página para paginación de datos de consulta de movimientos") Integer pagina,
			@Parameter(hidden = true) @Valid Cabecera cabecera, @Parameter(hidden = true) @Valid DepositoID id,
			@RequestParam @Parameter(name = "celular", description = "Celular al que está asociado el depósito electrónico para consultar los movimientos") @ConstraintCelular(message = "{celular.isNumeric}") String celular);

	@Parameters(value = {

			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "hash-message", required = false, description = "Código de autenticación de mensajes basado en Hash-Message"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-cliente-corresponsal", required = true, description = "Dirección IP del cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-servidor-corresponsal", required = true, description = "Dirección IP del servidor del corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-cliente-corresponsal", required = true, description = "Dirección MAC cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-servidor-corresponsal", required = true, description = "Dirección MAC corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "numero-referencia", required = true, description = "Número de referencia de la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "pais-origen", required = true, description = "País origen donde se realiza la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH, name = "id", required = true, description = "Identificador único de transacción para depósito electrónico") })
	@Operation(summary = "Transacción que permite realizar débitos o retiros sobre un depósito electrónico del Banco Caja Social", description = "Transacción que permite realizar débitos o retiros sobre un depósito electrónico del Banco Caja Social")
	public ResponseEntity<NumeroAutorizacion> retiroDepositoElectronico(@Valid @RequestBody Debito request,
			@Parameter(hidden = true) @Valid Cabecera cabecera, @Parameter(hidden = true) @Valid DepositoID id);

	@Parameters(value = {
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "hash-message", required = false, description = "Código de autenticación de mensajes basado en Hash-Message"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-cliente-corresponsal", required = true, description = "Dirección IP del cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-servidor-corresponsal", required = true, description = "Dirección IP del servidor del corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-cliente-corresponsal", required = true, description = "Dirección MAC cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-servidor-corresponsal", required = true, description = "Dirección MAC corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "numero-referencia", required = true, description = "Número de referencia de la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "pais-origen", required = true, description = "País origen donde se realiza la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH, name = "id", required = true, description = "Identificador único de transacción para depósito electrónico") })
	@Operation(summary = "Transacción que permite realizar consignaciones o créditos sobre un depósito electrónico del Banco Caja Social", description = "Transacción que permite realizar consignaciones o créditos sobre un depósito electrónico del Banco Caja Social")
	public ResponseEntity<NumeroAutorizacion> consignacionDepositoElectronico(@Valid @RequestBody Credito request,
			@Parameter(hidden = true) @Valid Cabecera cabecera, @Parameter(hidden = true) @Valid DepositoID id);

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful Operation") })
	@Parameters(value = {
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "hash-message", required = false, description = "Código de autenticación de mensajes basado en Hash-Message"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-cliente-corresponsal", required = true, description = "Dirección IP del cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-servidor-corresponsal", required = true, description = "Dirección IP del servidor del corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-cliente-corresponsal", required = true, description = "Dirección MAC cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-servidor-corresponsal", required = true, description = "Dirección MAC corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "numero-referencia", required = true, description = "Número de referencia de la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "pais-origen", required = true, description = "País origen donde se realiza la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH, name = "id", required = true, description = "Identificador único de transacción para depósito electrónico") })
	@Operation(summary = "Transacción que permite obtener el código OTC para las transacciones financieras sobre el depósito electrónico", description = "Transacción que permite obtener el código OTC para las transacciones financieras sobre el depósito electrónico")
	public ResponseEntity<TiempoVidaToken> obtenerOtcRetiro(@Valid @RequestBody Otc request,
			@Parameter(hidden = true) @Valid Cabecera cabecera, @Valid DepositoID id);



			@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Solicitud de Medio de Manejo Correcta") })
	@Parameters(value = {
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "hash-message", required = false, description = "Código de autenticación de mensajes basado en Hash-Message"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-cliente-corresponsal", required = true, description = "Dirección IP del cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "ip-servidor-corresponsal", required = true, description = "Dirección IP del servidor del corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-cliente-corresponsal", required = true, description = "Dirección MAC cliente"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "mac-servidor-corresponsal", required = true, description = "Dirección MAC corresponsal"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "numero-referencia", required = true, description = "Número de referencia de la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.HEADER, name = "pais-origen", required = true, description = "País origen donde se realiza la transacción"),
			@Parameter(schema = @Schema(implementation = String.class), in = ParameterIn.PATH, name = "id", required = true, description = "Identificador único de transacción para depósito electrónico") })
	@Operation(summary = "Transacción que permite solicitar el medio de Manejo para un depósito electrónico", description = "Transacción que permite obtener el código OTC para las transacciones financieras sobre el depósito electrónico")
	public ResponseEntity<Void> solicitarMedioDeManejo(@Valid @RequestBody MedioManejo request,
			@Parameter(hidden = true) @Valid Cabecera cabecera, @Valid DepositoID id);
}
