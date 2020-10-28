package co.com.bcs.apibcs.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.com.bcs.apibcs.exception.Error500Exception;
import co.com.bcs.apibcs.mappers.ConsultarMovimientosDepositoMapper;
import co.com.bcs.apibcs.mappers.SolicitarDepositoElectronicoMapper;
import co.com.bcs.apibcs.mappers.ValidarAlcanciaRequestMapper;
import co.com.bcs.apibcs.services.dto.ConsultarMovimientosDepositoRequest;
import co.com.bcs.apibcs.services.dto.ConsultarMovimientosDepositoResponse;
import co.com.bcs.apibcs.services.dto.ConsultarSaldoDepositoRequest;
import co.com.bcs.apibcs.services.dto.SolicitarDeposito;
import co.com.bcs.apibcs.services.dto.ValidarDepositoRequest;
import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESRequest;
import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESResponse;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaRequestType;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaResponseType;
import co.com.bcs.backend.services.client.sam.FinalizarRegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.RegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaRequestType;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaResponseType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AlcanciaAmigaServiceImpl implements AlcanciaAmigaService {

	private WsClientService wsClientService;

	private SolicitarDepositoElectronicoMapper solicitarDepositoElectronicoMapper;

	private ConsultarMovimientosDepositoMapper consultarMovimientosDepositoMapper;

	private ValidarAlcanciaRequestMapper validarAlcanciaRequestMapper;

	@Override
	public boolean validarDeposito(ValidarDepositoRequest request) {

		ValidarAlcanciaRequestType alcanciaRequest = validarAlcanciaRequestMapper.toValidarAlcanciaRequest(request);
		ValidarAlcanciaResponseType response = wsClientService.validarDepositoElectronico(alcanciaRequest);
		return "SI".equalsIgnoreCase(response.getTieneAlcancia());
	}

	@Override
	public void solicitarDeposito(SolicitarDeposito request) {

		RegistroClienteRequestType requestWS = solicitarDepositoElectronicoMapper.toRegistroClienteRequestType(request);
		wsClientService.solicitarDepositoElectronico(requestWS);
		finalizarSolicitudDepositoElectronico(request);

	}

	private boolean finalizarSolicitudDepositoElectronico(SolicitarDeposito request) {
		Optional<String> claveEncriptada = encriptarClave(request);
		if (!claveEncriptada.isPresent()) {
			throw new Error500Exception("Error al procesar la clave encriptada", "0001",
					"En este momento no podemos atenderlo");
		}

		/*
		 * // Temporal, debe removerse
		 * cabeceraEntrada.getInvocador().setCanalOrigen("07");
		 * cabeceraEntrada.getInvocador().setSubcanal("6"); //
		 */

		FinalizarRegistroClienteRequestType requestWS = solicitarDepositoElectronicoMapper
				.toFinalizarRegistroClienteRequestType(request, claveEncriptada.get());
		wsClientService.finalizarSolicitarDepositoElectronico(requestWS);
		return true;
	}

	private Optional<String> encriptarClave(SolicitarDeposito request) {

		EncriptarMensajeAESRequest requestWS = solicitarDepositoElectronicoMapper.toEncriptarMensajeAESRequest(
				request.getCabecera(), new String(Base64.getDecoder().decode(request.getClave())));
		EncriptarMensajeAESResponse response = wsClientService.encriptarMensaje(requestWS);
		return Optional.ofNullable(response.getEncriptacionAES());
	}

	@Override
	public ConsultarMovimientosDepositoResponse consultarMovimientos(ConsultarMovimientosDepositoRequest request) {
		ConsultarMovimientosAlcanciaRequestType requestWS = consultarMovimientosDepositoMapper
				.consultarMovimientos(request);
		ConsultarMovimientosAlcanciaResponseType response = wsClientService.consultarMovimientosAlcancia(requestWS);
		return consultarMovimientosDepositoMapper.consultarMovimientosResponse(response);

	}

	@Override
	public String consultarSaldo(ConsultarSaldoDepositoRequest request) {

		ConsultarMovimientosAlcanciaRequestType requestWS = consultarMovimientosDepositoMapper
				.consultarMovimientos(request);
		ConsultarMovimientosAlcanciaResponseType response = wsClientService.consultarMovimientosAlcancia(requestWS);

		if (null == response.getSaldo()) {
			return "0";
		}

		BigDecimal division = response.getSaldo().divide(BigDecimal.valueOf(100));

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setGroupingUsed(false);
		return df.format(division);

	}

}