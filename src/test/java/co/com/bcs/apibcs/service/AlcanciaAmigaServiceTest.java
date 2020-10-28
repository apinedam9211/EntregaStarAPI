package co.com.bcs.apibcs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import co.com.bcs.apibcs.exception.BackendException;
import co.com.bcs.apibcs.mappers.*;
import co.com.bcs.apibcs.services.AlcanciaAmigaServiceImpl;
import co.com.bcs.apibcs.services.WsClientService;
import co.com.bcs.apibcs.services.dto.ConsultarSaldoDepositoRequest;
import co.com.bcs.apibcs.services.dto.ValidarDepositoRequest;
import co.com.bcs.backend.services.client.sam.CabeceraEntrada;
import co.com.bcs.backend.services.client.sam.CabeceraSalida;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaRequestType;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaResponseType;
import co.com.bcs.backend.services.client.sam.DatosPaginacionEntType;
import co.com.bcs.backend.services.client.sam.IdentificacionType;
import co.com.bcs.backend.services.client.sam.NombreCompletoType;
import co.com.bcs.backend.services.client.sam.RespuestaError;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaResponseType;

@ActiveProfiles("tomcat")
class AlcanciaAmigaServiceTest {

	@Mock
	private WsClientService wsClientService;

	@Mock
	private ValidarAlcanciaRequestMapper validarAlcanciaRequestMapper;

	@Mock
	private SolicitarDepositoElectronicoMapper solicitarDepositoElectronicoMapper;

	private AlcanciaAmigaServiceImpl alcanciaAmigaService;
	@Mock
	private ConsultarMovimientosDepositoMapper depositoMapper;

	@Mock
	private ConsultarMovimientosDepositoMapper consultarMovimientosDepositoMapper;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		alcanciaAmigaService = new AlcanciaAmigaServiceImpl(wsClientService, solicitarDepositoElectronicoMapper,
				depositoMapper, validarAlcanciaRequestMapper);
	}

	@Test
	void testValidarDepositoExitoso() {
		ValidarDepositoRequest request = new ValidarDepositoRequest();
		ValidarAlcanciaResponseType response = new ValidarAlcanciaResponseType();

		CabeceraSalida cabeceraSalida = new CabeceraSalida();
		cabeceraSalida.setTipoRespuesta("OK");
		cabeceraSalida.setRespuestaError(new RespuestaError());
		response.setCabeceraSalida(cabeceraSalida);
		response.setCelular("3046477974");
		response.setCorreoElectronico("apineda@psl.com");
		response.setFlagActualizaCorreo("SI");
		response.setFlagRegistro("SI");
		NombreCompletoType nombre = new NombreCompletoType();
		nombre.setPrimerApellido("pineda");
		nombre.setPrimerNombre("andres");
		response.setNombre(nombre);
		response.setTieneAlcancia("SI");

		Mockito.when(wsClientService.validarDepositoElectronico(any())).thenReturn(response);
		alcanciaAmigaService.validarDeposito(request);
		verify(wsClientService, times(1)).validarDepositoElectronico(any());

	}

	@Test
	void testValidarDepositoFallido() {
		ValidarDepositoRequest request = new ValidarDepositoRequest();
		ValidarAlcanciaResponseType response = new ValidarAlcanciaResponseType();

		CabeceraSalida cabeceraSalida = new CabeceraSalida();
		cabeceraSalida.setTipoRespuesta("OK");
		cabeceraSalida.setRespuestaError(new RespuestaError());
		response.setCabeceraSalida(cabeceraSalida);
		response.setCelular("3046477974");
		response.setCorreoElectronico("apineda@psl.com");
		response.setFlagActualizaCorreo("SI");
		response.setFlagRegistro("SI");
		NombreCompletoType nombre = new NombreCompletoType();
		nombre.setPrimerApellido("pineda");
		nombre.setPrimerNombre("andres");
		response.setNombre(nombre);
		response.setTieneAlcancia("NO");

		Mockito.when(wsClientService.validarDepositoElectronico(any())).thenReturn(response);

		assertFalse(alcanciaAmigaService.validarDeposito(request));
	}

	@Test
	void throwBackendException() {

		ValidarDepositoRequest request = new ValidarDepositoRequest();

		ValidarAlcanciaResponseType responseWS = new ValidarAlcanciaResponseType();
		CabeceraSalida cabeceraSalida = new CabeceraSalida();
		cabeceraSalida.setTipoRespuesta("ER");
		RespuestaError respuestaError = new RespuestaError();
		respuestaError.setCodigoError("codigoError");
		respuestaError.setDescripcionError("descripcionError");
		respuestaError.setTipoError("tipoError");
		cabeceraSalida.setRespuestaError(respuestaError);
		responseWS.setCabeceraSalida(cabeceraSalida);
		when(wsClientService.validarDepositoElectronico(any())).thenThrow(new BackendException());

		assertThrows(BackendException.class, () -> alcanciaAmigaService.validarDeposito(request));
		verify(wsClientService, times(1)).validarDepositoElectronico(any());

	}

	@Test
	void testConsultarSaldo() {

		ConsultarSaldoDepositoRequest request = new ConsultarSaldoDepositoRequest();
		
		ConsultarMovimientosAlcanciaRequestType requestWS = new ConsultarMovimientosAlcanciaRequestType();
		when(consultarMovimientosDepositoMapper.consultarMovimientos(any(ConsultarSaldoDepositoRequest.class))).thenReturn(requestWS);
		ConsultarMovimientosAlcanciaResponseType responseWS = new ConsultarMovimientosAlcanciaResponseType();
		responseWS.setSaldo(BigDecimal.valueOf(10000));
		when(wsClientService.consultarMovimientosAlcancia(any())).thenReturn(responseWS);

		String saldo = alcanciaAmigaService.consultarSaldo(request);
		assertEquals("100,00", saldo);

	}

}
