package co.com.bcs.apibcs.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.zalando.logbook.HttpLogFormatter;

import co.com.bcs.apibcs.ApibcsApplication;
import co.com.bcs.apibcs.config.ConfigBean;
import co.com.bcs.apibcs.config.WebConfig;
import co.com.bcs.apibcs.controller.v1.depositoelectronico.*;
import co.com.bcs.apibcs.dto.v1.entities.Credito;
import co.com.bcs.apibcs.exception.BackendException;
import co.com.bcs.apibcs.mappers.ControllerMapper;
import co.com.bcs.apibcs.mappers.ControllerMapperImpl;
import co.com.bcs.apibcs.services.dto.*;
import co.com.bcs.apibcs.services.AlcanciaAmigaService;
import co.com.bcs.apibcs.services.LoggingService;
import co.com.bcs.apibcs.services.MedioManejoService;
import co.com.bcs.apibcs.services.OTCTokenService;
import co.com.bcs.apibcs.services.TransaccionesFinancierasService;

@WebMvcTest(controllers = DepositoElectronicoController.class)
@ActiveProfiles({"NoAuth" ,"tomcat"})
@Import(ControllerMapperImpl.class)
@ContextConfiguration(classes = { ApibcsApplication.class, ConfigBean.class, WebConfig.class, ControllerMapper.class })
class TransaccionesFinancierasControllerTest {


        @MockBean 
        private HttpLogFormatter HttpLogFormatter;

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private AlcanciaAmigaService alcanciaService;

        @MockBean
        private TransaccionesFinancierasService service;

        @MockBean
        private OTCTokenService otcTokenService;

        @MockBean
        private ControllerMapper controllerMapper;

        @MockBean
	private MedioManejoService medioManejo;

        @MockBean
        private LoggingService logging;

        public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

        @Test
        @WithMockUser(authorities = { "ROLE_API_BANCO" })
        void testFaltaParametros() throws Exception {

                DebitoDepositoElectronicoRequest requestDeposito = new DebitoDepositoElectronicoRequest();
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
                ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
                String content = ow.writeValueAsString(requestDeposito);

                mockMvc.perform(post(
                                "/v1/depositos-electronicos/Q0MtMTAyMjM5MTEyNS1tYWZvcmVyb29AZ21haWwuY29tLTMxNjQ5OTk4MDE=/debito")
                                                .contentType(APPLICATION_JSON_UTF8).content(content))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

        @Test
        @WithMockUser(authorities = { "ROLE_API_BANCO" })
        void realizarCreditoNoParemeters() throws Exception {

                CreditoDepositoElectronicoRequest request = new CreditoDepositoElectronicoRequest();

                Identificacion cliente = new Identificacion();
                cliente.setId("1235");
                cliente.setTipoId(TipoIdentificacion.CC.name());
                request.setCliente(cliente);
                request.setMonto("1000");
                request.setReferencia("12351");

                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
                ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
                String content = ow.writeValueAsString(request);

                mockMvc.perform(post(
                                "/v1/depositos-electronicos/Q0MtMTAyMjM5MTEyNS1tYWZvcmVyb29AZ21haWwuY29tLTMxNjQ5OTk4MDE=/credito")
                                                .contentType(APPLICATION_JSON_UTF8).content(content))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        }

        @Test
        @WithMockUser(authorities = { "ROLE_API_BANCO" })
        void realizarDebito() throws Exception {

                DebitoDepositoElectronicoRequest requestDeposito = new DebitoDepositoElectronicoRequest();
                Identificacion cliente = new Identificacion();
                cliente.setId("1235");
                cliente.setTipoId(TipoIdentificacion.CC.name());
                requestDeposito.setCliente(cliente);
                requestDeposito.setMonto("1000");
                requestDeposito.setPinBlock("B2C3D4E5F6G4G3D3");
                requestDeposito.setReferencia("12351");

                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
                ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
                String content = ow.writeValueAsString(requestDeposito);

                mockMvc.perform(post(
                                "/v1/depositos-electronicos/Q0MtMTAyMjM5MTEyNS1tYWZvcmVyb29AZ21haWwuY29tLTMxNjQ5OTk4MDE=/debito")
                                                .contentType(APPLICATION_JSON_UTF8).content(content))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        }

        @Test
        @WithMockUser(authorities = { "ROLE_API_BANCO" })
        void realizarCredito() throws Exception {

                Credito request = new Credito();

                request.setMonto("1000");
                request.setReferencia("12351");

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("codigo-corresponsal", "123412");
                httpHeaders.set("codigo-terminal", "12345");
                httpHeaders.set("hash-message", "");
                httpHeaders.set("ip-cliente-corresponsal", "192.168.169.12");
                httpHeaders.set("ip-servidor-corresponsal", "192.168.169.12");
                httpHeaders.set("mac-cliente-corresponsal", "00:00:00:a1:2b:cc");
                httpHeaders.set("mac-servidor-corresponsal", "00:00:00:a1:2b:cc");
                httpHeaders.set("numero-referencia", "APIJ1236567");
                httpHeaders.set("pais-origen", "CO");

                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
                ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
                String content = ow.writeValueAsString(request);

                when(service.credito(any())).thenThrow(new BackendException());
                mockMvc.perform(post(
                                "/v1/depositos-electronicos/CC-1022391125/credito")
                                                .contentType(APPLICATION_JSON_UTF8).headers(httpHeaders)
                                                .content(content))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

}