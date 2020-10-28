package co.com.bcs.apibcs.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.nio.charset.Charset;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.zalando.logbook.HttpLogFormatter;

import co.com.bcs.apibcs.ApibcsApplication;
import co.com.bcs.apibcs.config.ConfigBean;
import co.com.bcs.apibcs.config.WebConfig;
import co.com.bcs.apibcs.controller.v1.depositoelectronico.DepositoElectronicoController;
import co.com.bcs.apibcs.dto.v1.entities.MedioManejo;
import co.com.bcs.apibcs.dto.v1.entities.SolicitudDeposito;
import co.com.bcs.apibcs.exception.BackendException;
import co.com.bcs.apibcs.mappers.ControllerMapper;
import co.com.bcs.apibcs.services.AlcanciaAmigaService;
import co.com.bcs.apibcs.services.LoggingService;
import co.com.bcs.apibcs.services.MedioManejoService;
import co.com.bcs.apibcs.services.OTCTokenService;
import co.com.bcs.apibcs.services.TransaccionesFinancierasService;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = DepositoElectronicoController.class)
@ActiveProfiles({ "NoAuth", "tomcat" })
class DepositoElectronicoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private HttpLogFormatter HttpLogFormatter;

	@MockBean
	private AlcanciaAmigaService alcanciaService;

	@MockBean
	private TransaccionesFinancierasService service;

	@MockBean
	private LoggingService logging;

	@MockBean
	private MedioManejoService medioManejo;

	@MockBean
	private ControllerMapper validarAlcanciaRequestMapper;

	@MockBean
	private OTCTokenService otcTokenService;

	private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	@WithMockUser(authorities = { "ROLE_API_BANCO" })
	void consultarDepositoFaltanParametros() throws Exception {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("codigo-corresponsal", "123412");
		httpHeaders.set("codigo-terminal", "");
		httpHeaders.set("hash-message", "");
		httpHeaders.set("ip-cliente-corresponsal", "192.168.169.12");
		httpHeaders.set("ip-servidor-corresponsal", "192.168.169.12");
		httpHeaders.set("mac-cliente-corresponsal", "00:00:00:a1:2b:cc");
		httpHeaders.set("mac-servidor-corresponsal", "00:00:00:a1:2b:cc");
		httpHeaders.set("numero-referencia", "APIJ1236567");
		httpHeaders.set("tipo-id", "CC");
		httpHeaders.set("id", "1022391125");
		httpHeaders.set("correo", "maforeroogmail.com");
		httpHeaders.set("celular", "3164999801");
		httpHeaders.set("pais-origen", "CO");

		mockMvc.perform(get("/v1/depositos-electronicos").contentType(APPLICATION_JSON_UTF8).headers(httpHeaders))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	@WithMockUser(authorities = { "ROLE_API_BANCO" })
	void consultarDepositoOKParametros() throws Exception {

		HttpHeaders httpHeaders = getHeaders();

		when(alcanciaService.validarDeposito(any())).thenReturn(true);

		mockMvc.perform(get(
				"/v1/depositos-electronicos?tipoDocumento=CC&numeroDocumento=1022391125&celular=3164999801&correo=maforeroo@gmail.com")
						.contentType(APPLICATION_JSON_UTF8).headers(httpHeaders))
				.andExpect(MockMvcResultMatchers.status().isOk());

		verify(alcanciaService, times(1)).validarDeposito(any());

	}

	@Test
	@WithMockUser(authorities = { "ROLE_API_BANCO" })
	void consultarDepositoERResponse() throws Exception {

		HttpHeaders httpHeaders = getHeaders();

		doThrow(new BackendException("message", "codigoError", "descripcionUsuario")).when(alcanciaService)
				.validarDeposito(any());
		mockMvc.perform(get("/v1/depositos-electronicos/CC-1022391125?correo=maforeroo@gmail.com&celular=3164999801")
				.contentType(APPLICATION_JSON_UTF8).headers(httpHeaders))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

		verify(alcanciaService, times(1)).validarDeposito(any());

	}

	@Test
	@WithMockUser(authorities = { "ROLE_API_BANCO" })
	void consultarMovimientosIdNoValido() throws Exception {
		HttpHeaders httpHeaders = getHeaders();

		mockMvc.perform(get("/v1/depositos-electronicos/12345/movimientos?pagina=1").contentType(APPLICATION_JSON_UTF8)
				.headers(httpHeaders)).andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	@Test
	@WithMockUser(authorities = { "ROLE_API_BANCO" })
	void solicitarMedioManejo() throws Exception {
		HttpHeaders httpHeaders = getHeaders();
		MedioManejo request = new MedioManejo();
		request.setCiudad("ciudad");
		request.setCorreo("maforeroo@gmail.com");
		request.setDireccion("CL 3 A 51 80");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String content = ow.writeValueAsString(request);

		doNothing().when(medioManejo).crearMedioManejo(any());

		mockMvc.perform(post("/v1/depositos-electronicos/CC-12345/medio-manejo").content(content)
				.contentType(APPLICATION_JSON_UTF8).headers(httpHeaders)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void solicitarDepositoFechaExpedicionIncorrecta() throws Exception {

		HttpHeaders httpHeaders = getHeaders();

		SolicitudDeposito request = new SolicitudDeposito();
		request.setCelular("3164999801");
		request.setClave("NTc0Ng==");
		request.setCorreoElectronico("maforeroo@gmail.com");
		request.setFechaExpedicionDocumento("19940313");
		request.setNumeroDocumento("1022391125");
		request.setGenero("M");
		request.setPrimerNombre("Manuel");
		request.setPrimerApellido("Forero");
		request.setTipoDocumento("CC");
		request.setFechaNacimiento("1994-03-13");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String content = ow.writeValueAsString(request);
		ResultActions result = mockMvc
				.perform(post("/v1/depositos-electronicos").contentType(APPLICATION_JSON_UTF8).headers(httpHeaders)
						.content(content))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andExpect(jsonPath("$.errorCode", is("VAL")))
				.andExpect(
						jsonPath("$.descriptionError", is("La fecha de expedición debe tener el formato YYYY-MM-DD.")))
				.andExpect(jsonPath("$.status", is("ER")));

		result.andReturn().getResponse().getContentAsString();

	}

	private HttpHeaders getHeaders() {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("codigo-corresponsal", "123412");
		httpHeaders.set("codigo-terminal", "1234");
		httpHeaders.set("hash-message", "");
		httpHeaders.set("ip-cliente-corresponsal", "192.168.169.12");
		httpHeaders.set("ip-servidor-corresponsal", "192.168.169.12");
		httpHeaders.set("mac-cliente-corresponsal", "00:00:00:a1:2b:cc");
		httpHeaders.set("mac-servidor-corresponsal", "00:00:00:a1:2b:cc");
		httpHeaders.set("numero-referencia", "APIJ1236567");
		httpHeaders.set("tipo-id", "CC");
		httpHeaders.set("id", "1022391125");
		httpHeaders.set("correo", "maforeroo@gmail.com");
		httpHeaders.set("celular", "3164999801");
		httpHeaders.set("pais-origen", "CO");
		return httpHeaders;
	}
}