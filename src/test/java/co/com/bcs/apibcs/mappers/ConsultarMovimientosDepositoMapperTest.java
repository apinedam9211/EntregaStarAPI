package co.com.bcs.apibcs.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import co.com.bcs.apibcs.services.dto.*;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaRequestType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("tomcat")
public class ConsultarMovimientosDepositoMapperTest {

    @Autowired
    ConsultarMovimientosDepositoMapper mapper;

    @Test
    @WithMockUser(authorities = { "ROLE_API_BANCO" }, username = "userTest")
    void consultarMovimientosSaldoDepositoRequest() {

        ConsultarSaldoDepositoRequest request = new ConsultarSaldoDepositoRequest();
        request.setCelular("3164999801");
        request.setCliente(Identificacion.builder().id("1022391125").tipoId("CC").build());
        ConsultarMovimientosAlcanciaRequestType result = mapper.consultarMovimientos(request);
        assertEquals("3164999801", result.getCelular());
    }

}