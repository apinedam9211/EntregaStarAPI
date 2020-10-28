package co.com.bcs.apibcs.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zalando.logbook.HttpLogFormatter;

import co.com.bcs.apibcs.controller.v1.depositoelectronico.DepositoElectronicoController;
import co.com.bcs.apibcs.dto.v1.entities.Cabecera;
import co.com.bcs.apibcs.dto.v1.entities.Otc;
import co.com.bcs.apibcs.mappers.ControllerMapper;
import co.com.bcs.apibcs.services.AlcanciaAmigaService;
import co.com.bcs.apibcs.services.LoggingService;
import co.com.bcs.apibcs.services.MedioManejoService;
import co.com.bcs.apibcs.services.OTCTokenService;
import co.com.bcs.apibcs.services.TransaccionesFinancierasService;

@WebMvcTest(controllers = DepositoElectronicoController.class)
@ActiveProfiles({ "tomcat", "NoAuth" })
class TokenControllerIntegrationTest {

        @Autowired
        private WebApplicationContext webContext;

        private MockMvc mockMvc;

        @MockBean
        private AlcanciaAmigaService alcanciaService;

        @MockBean
        private TransaccionesFinancierasService service;

        @MockBean
        private OTCTokenService otcTokenService;

        @MockBean
        private ControllerMapper validarAlcanciaRequestMapper;

        @MockBean
        private MedioManejoService medioManejo;

        @MockBean
        private HttpLogFormatter HttpLogFormatter;
        @MockBean
        private LoggingService logging;

        public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

        @BeforeEach
        void setupMockContext() throws Exception {
                mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
        }

        @Test
        @WithMockUser(authorities = { "ROLE_API_BANCO" }, username = "role")
        void badRequestNotHeaders() throws Exception {

                mockMvc.perform(post("/v1/depositos-electronicos/CC-1022391125-3164999801-maforeroo/otc")

                                .contentType(APPLICATION_JSON_UTF8))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

        @Test
        @WithMockUser(authorities = { "ROLE_API_BANCO" }, username = "role")
        void solicitarToken() throws Exception {

                Otc request = new Otc();
                request.setValorRetiro("1000");
                request.setCorreo("maforeroo@gmail.com");
                request.setCelular("3164999801");
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
                ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
                String content = ow.writeValueAsString(request);

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

                Cabecera cabecera = new Cabecera();

                cabecera.setHashMessage("hashMessage");
                cabecera.setIpClienteCorresponsal("192.168.169.12");
                cabecera.setIpServidorCorresponsal("192.168.169.12");
                cabecera.setMacClienteCorresponsal("00:00:00:a1:2b:cc");
                cabecera.setMacServidorCorresponsal("00:00:00:a1:2b:cc");
                cabecera.setNumeroReferencia("APIJ1236567");
                cabecera.setPaisOrigen("CO");

                when(otcTokenService.solicitarToken(any())).thenReturn("60");

                ResultActions result = mockMvc.perform(post("/v1/depositos-electronicos/CC-1022391125/otc")
                                .contentType(APPLICATION_JSON_UTF8).content(content).headers(httpHeaders));
                result.andExpect(MockMvcResultMatchers.status().isOk());
                result.andExpect(MockMvcResultMatchers.content().json("{'tiempoVidaToken' : '60'}"));
                // validar la repsuest

        }

        @Test
        @WithMockUser(authorities = { "ROLE_API_BANCO" }, username = "role")
        void solicitarTokenSinCabeceras() throws Exception {

                Otc request = new Otc();
                request.setValorRetiro("1000");
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
                ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
                String content = ow.writeValueAsString(request);

                Cabecera cabecera = new Cabecera();

                cabecera.setHashMessage("hashMessage");
                cabecera.setIpClienteCorresponsal("192.168.169.12");
                cabecera.setIpServidorCorresponsal("192.168.169.12");
                cabecera.setMacClienteCorresponsal("00:00:00:a1:2b:cc");
                cabecera.setMacServidorCorresponsal("00:00:00:a1:2b:cc");
                cabecera.setNumeroReferencia("APIJ1236567");
                cabecera.setPaisOrigen("CO");

                mockMvc.perform(post(
                                "/v1/depositos-electronicos/Q0MtMTAyMjM5MTEyNS1tYWZvcmVyb29AZ21haWwuY29tLTMxNjQ5OTk4MDE=/otc")
                                                .contentType(APPLICATION_JSON_UTF8).content(content))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }

}