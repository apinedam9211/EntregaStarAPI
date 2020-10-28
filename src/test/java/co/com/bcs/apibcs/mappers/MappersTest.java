package co.com.bcs.apibcs.mappers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import co.com.bcs.apibcs.services.dto.SolicitarDeposito;
import co.com.bcs.apibcs.services.dto.*;
import co.com.bcs.backend.services.client.sam.FinalizarRegistroClienteRequestType;
import co.com.bcs.backend.services.client.samotc.IdentificacionType;
import co.com.bcs.backend.services.client.samotc.SolicitarOTCRetiroRequestType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("tomcat")
class MappersTest {

	@Autowired
	private SolicitarDepositoElectronicoMapper solicitarDepositoElectronicoMapper;

	@Autowired
	private CabecerasMapper cabecerasMapper;

	@Autowired
	private SolicitarOTCRetiroRequestTypeMapper solicitarOTCRetiroRequestTypeMapper;

	@Autowired
	private IdentificacionTypeMapper identificacionMapper;

	@Test
	void testIdentificacionTypeOTCMapper() {
		String tipoId = "CC";
		String numId = "1010101010";
		Identificacion identificacion = Identificacion.builder().tipoId(tipoId).id(numId).build();
		IdentificacionType identificacionType = identificacionMapper.toIdentificacionTypeOTC(identificacion);

		assertEquals(identificacionType.getNumIdentificacion(), numId);
		assertEquals(identificacionType.getTpIdentidicacion(), tipoId);
	}

	@Test
	@WithMockUser(authorities = { "ROLE_API_BANCO" }, username = "userTest")
	void testSolicitarOTCRetiroRequestTypeMapper() {

		String tipoId = "CC";
		String numId = "1010101010";
		String codigoTx = "01";

		String celular = "3103103102";
		OTCRetiroRequest request = new OTCRetiroRequest();
		request.setCelular(celular);
		request.setValorRetiro("26");
		Identificacion identificacionCliente = Identificacion.builder().tipoId(tipoId).id(numId).build();
		request.setIdentificacionCliente(identificacionCliente);
		SolicitarOTCRetiroRequestType requestWS = solicitarOTCRetiroRequestTypeMapper
				.toRegistroClienteRequestType(request);

		assertEquals(requestWS.getIdentificacion().getNumIdentificacion(), numId);
		assertEquals(requestWS.getDatosRetiro().getCelular(), celular);
		assertEquals(requestWS.getCodigoTx(), codigoTx);
	}

	@Test
	@WithMockUser(authorities = { "ROLE_API_BANCO" }, username = "userTest")
	void testFinalizarDepositoElectronico() {
		String tipoId = "CC";
		String numId = "1010101010";

		SolicitarDeposito solicitarDepositoRequest = new SolicitarDeposito();
		solicitarDepositoRequest.setIdentificacionCliente(Identificacion.builder().id(numId).tipoId(tipoId).build());
		FinalizarRegistroClienteRequestType requestWS = solicitarDepositoElectronicoMapper
				.toFinalizarRegistroClienteRequestType(solicitarDepositoRequest, "1234687654");

		assertEquals("1234687654", requestWS.getClave());
		assertEquals(requestWS.getIdCliente().getNumIdentificacion(), numId);
		assertEquals("", requestWS.getLenddoAID());
	}

	@Test
	void cabeceraAndUsernameNull() {
		co.com.bcs.backend.services.client.sam.CabeceraEntrada cabecera = cabecerasMapper.toCabeceraEntrada(null);
		assertNull(cabecera, "Cabecera null");
	}

	@Test
	@WithMockUser(authorities = { "ROLE_API_BANCO" }, username = "123")
	void cabeceraNull() {
		co.com.bcs.backend.services.client.sam.CabeceraEntrada cabecera = cabecerasMapper.toCabeceraEntrada(null);
		assertNull(cabecera);

	}

}
