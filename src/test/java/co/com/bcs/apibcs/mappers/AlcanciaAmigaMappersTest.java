package co.com.bcs.apibcs.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sun.xml.messaging.saaj.util.Base64;
import co.com.bcs.apibcs.services.dto.*;
import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESRequest;
import co.com.bcs.backend.services.client.sam.FinalizarRegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.RegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaRequestType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("tomcat")
class AlcanciaAmigaMappersTest {

	@Autowired
	private ValidarAlcanciaRequestMapper validarAlcanciaRequestMapper;

	@Autowired
	private SolicitarDepositoElectronicoMapper solicitarDepositoElectronicoMapper;

	@Test
	@WithMockUser(authorities = { "ROLE_API_BANCO" }, username = "userTest")
	void testValidarAlcanciaRequestType() {

		;

		ValidarAlcanciaRequestType request = createRequestValidarAlcancia(ValidarDepositoRequest.builder()
				.identificacion(Identificacion.builder().tipoId("CC").id("11101110").build())
				.correo("correoEmail@email.com").cabecera(new Cabeceras()). build());
		assertEquals("11101110", request.getIdentificacion().getNumIdentificacion());
		assertEquals("CC", request.getIdentificacion().getTpIdentidicacion());
		assertEquals("0670", request.getCabeceraEntrada().getInvocador().getCodigoOficina());
		assertEquals("correoEmail@email.com", request.getCorreoElectronico());
		assertEquals("userTest", request.getCabeceraEntrada().getSeguridad().getUsuario());
	}

	@Test
	@WithMockUser(authorities = { "ROLE_API_BANCO" }, username = "userTest")
	void testRegistroClienteRequestType() {
		SolicitarDeposito rDepositoRequest = new SolicitarDeposito();
		Cabeceras cabecera = new Cabeceras();
		rDepositoRequest.setCabecera(cabecera);
		rDepositoRequest.setIdentificacionCliente(Identificacion.builder().id("999777444").tipoId("NI").build());
		RegistroClienteRequestType request = createRequestSolicitarDeposito(rDepositoRequest);
		assertEquals("999777444", request.getIdentificacion().getNumIdentificacion());
		assertEquals("NI", request.getIdentificacion().getTpIdentidicacion());
		assertEquals("0670", request.getCabeceraEntrada().getInvocador().getCodigoOficina());
		assertEquals("userTest", request.getCabeceraEntrada().getSeguridad().getUsuario());
	}

	@Test
	@WithMockUser(authorities = { "ROLE_API_BANCO" }, username = "userTest")
	void testEncriptarMensajeAESRequest() {
		SolicitarDeposito solicitarDeposito = new SolicitarDeposito();
		Cabeceras cabecera = new Cabeceras();
		solicitarDeposito.setCabecera(cabecera);
		EncriptarMensajeAESRequest request = createRequestEncriptarClave(solicitarDeposito);
		String clave = Base64.base64Decode("clave");
		assertEquals("userTest", request.getCabeceraEntrada().getSeguridad().getUsuario());
		assertEquals(clave, request.getTextoAEncriptar());
	}

	void testRinalizarSolicitudDepositoElectronico() {
		FinalizarRegistroClienteRequestType request = createRequestFinalizarSolicitudDeposito(
				new SolicitarDeposito());
		assertEquals("22233344", request.getIdCliente().getNumIdentificacion());
		assertEquals("CC", request.getIdCliente().getTpIdentidicacion());
		assertEquals("0670", request.getCabeceraEntrada().getInvocador().getCodigoOficina());
		assertEquals("clave", request.getClave());
		assertEquals("clave", request.getCabeceraEntrada().getSeguridad().getUsuario());
	}

	private RegistroClienteRequestType createRequestSolicitarDeposito(SolicitarDeposito request) {
		RegistroClienteRequestType requestWS = solicitarDepositoElectronicoMapper
				.toRegistroClienteRequestType(request);
		return requestWS;
	}

	private ValidarAlcanciaRequestType createRequestValidarAlcancia(ValidarDepositoRequest request) {

		ValidarAlcanciaRequestType requestWS = validarAlcanciaRequestMapper.toValidarAlcanciaRequest(request);
		return requestWS;
	}

	private FinalizarRegistroClienteRequestType createRequestFinalizarSolicitudDeposito(
		SolicitarDeposito request) {
		FinalizarRegistroClienteRequestType requestWS = solicitarDepositoElectronicoMapper
				.toFinalizarRegistroClienteRequestType(request, "clave");
		return requestWS;

	}

	private EncriptarMensajeAESRequest createRequestEncriptarClave(SolicitarDeposito request) {

		EncriptarMensajeAESRequest requestWS = solicitarDepositoElectronicoMapper
				.toEncriptarMensajeAESRequest(request.getCabecera(), Base64.base64Decode("clave"));
		return requestWS;

	}
}
