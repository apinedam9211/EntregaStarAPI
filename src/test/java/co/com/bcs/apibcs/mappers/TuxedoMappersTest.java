package co.com.bcs.apibcs.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import co.com.bcs.apibcs.services.dto.*;
import co.com.bcs.apibcs.tuxedo.objects.CreditoTuxedoRequest;
import co.com.bcs.apibcs.tuxedo.objects.RetiroTuxedoRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("tomcat")
class TuxedoMappersTest {

	@Autowired
	private TuxedoMappers mapper;

	@Test
	void requestOk() {

		DebitoDepositoElectronicoRequest requestDeposito = new DebitoDepositoElectronicoRequest();

		Identificacion cliente = new Identificacion();
		cliente.setId("1235");
		cliente.setTipoId(TipoIdentificacion.CC.name());
		requestDeposito.setCliente(cliente);
		requestDeposito.setMonto("1000");
		requestDeposito.setPinBlock("B2C3D4E5F6G4G3D3");
		requestDeposito.setReferencia("12351");

		RetiroTuxedoRequest resultMapper = mapper.toRetiroTuxedoRequest(requestDeposito);
		// constantes
		assertEquals("", resultMapper.getTokenQM());
		assertEquals(mapper.getSubCanalDebito(), resultMapper.getSubcanal());
		assertEquals("1110000000180", resultMapper.getCodigoEntidadAsociada());
		assertEquals("0132180TES10011P", resultMapper.getCardIssuer());
		assertEquals("0120180TES1+000", resultMapper.getDatosTerminal());
		assertEquals("10000002180", resultMapper.getCodigoInstitucion());
		assertEquals("1110000000180", resultMapper.getOtroParametro());
		assertEquals("01A000", resultMapper.getCodigoProcesamiento());
		assertEquals("13", resultMapper.getCanal());
		assertEquals("0200", resultMapper.getTipoTransaccion());
		assertTrue(resultMapper.getHora().matches("^[0-9]{6}$"));
		assertTrue(resultMapper.getFechaPosteo().matches("^[0-9]{4}$"));

		assertEquals("378900820000001235   =18122260000021100", resultMapper.getDatosRastreo());
		assertEquals("8900820000001235   ", resultMapper.getNumeroProducto());
		assertEquals("012351", resultMapper.getNumeroOriginador());
		assertEquals("100000", resultMapper.getValorTransaccion());
	}

	@Test
	void requestRetiroTuxedoNull() {

		RetiroTuxedoRequest result = mapper.toRetiroTuxedoRequest( null);
		assertNull(result, "request No es Nulo");
	}

	@Test
	void creditoDepositoElectronicoRequestNull() {
		CreditoTuxedoRequest result = mapper.toCreditoTuxedoRequest(null);
		assertNull(result);

	}

	@Test
	void creditoDepositoElectronicoRequestOK() {
		CreditoDepositoElectronicoRequest request = new CreditoDepositoElectronicoRequest();

		Cabeceras cabeceraEntrada = new Cabeceras();
		cabeceraEntrada.setCodigoTerminal("123577");

		Identificacion cliente = new Identificacion();
		cliente.setId("1235");
		cliente.setTipoId(TipoIdentificacion.CC.name());
		request.setCliente(cliente);
		request.setMonto("1000");

		request.setReferencia("12351");
		request.setCabecera(cabeceraEntrada);

		CreditoTuxedoRequest resultMapper = mapper.toCreditoTuxedoRequest(request);

		assertEquals(mapper.getSubCanalCredito(), resultMapper.getSubcanal());
		assertEquals("1110000000180", resultMapper.getCodigoEntidadAsociada());
		assertEquals("0132180TES10011P", resultMapper.getCardIssuer());
		assertEquals("0120180TES1+000", resultMapper.getDatosTerminal());
		assertEquals("10000002180", resultMapper.getCodigoInstitucion());
		assertEquals("1110000000180", resultMapper.getOtroParametro());
		assertEquals("090010", resultMapper.getCodigoProcesamiento());
		assertEquals("13", resultMapper.getCanal());
		assertEquals("0200", resultMapper.getTipoTransaccion());
		assertTrue(resultMapper.getHora().matches("^[0-9]{6}$"));
		assertTrue(resultMapper.getFechaPosteo().matches("^[0-9]{4}$"));

		assertEquals("378900820000001235   =18122260000021100", resultMapper.getDatosRastreo());
		assertEquals("8900820000001235   ", resultMapper.getNumeroProducto());
		assertEquals("012351", resultMapper.getNumeroOriginador());
		assertEquals("100000", resultMapper.getValorTransaccion());
		assertEquals("12351", resultMapper.getReferencia());
		assertEquals("123577", resultMapper.getIdTerminal());

	}

}