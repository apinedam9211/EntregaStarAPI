package co.com.bcs.apibcs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.com.bcs.apibcs.controller.v1.depositoelectronico.DepositoElectronicoController;
import co.com.bcs.apibcs.dto.v1.entities.*;
import co.com.bcs.apibcs.mappers.ControllerMapper;
import co.com.bcs.apibcs.services.AlcanciaAmigaService;
import co.com.bcs.apibcs.services.MedioManejoService;
import co.com.bcs.apibcs.services.OTCTokenService;
import co.com.bcs.apibcs.services.TransaccionesFinancierasService;

class TokenControllerTest {

    private DepositoElectronicoController controller;

    @Mock
    private AlcanciaAmigaService alcanciaAmigaService;

    @Mock
    private ControllerMapper controllerMapper;

    @Mock
    private OTCTokenService otcTokenService;

    @Mock
    private TransaccionesFinancierasService transaccionesFinancierasService;

    @Mock
    private MedioManejoService medioManejoService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        controller = new DepositoElectronicoController
        (alcanciaAmigaService, otcTokenService, transaccionesFinancierasService, medioManejoService, controllerMapper);
    }

    @Test
    void solicitarToken() {

        Otc request = new Otc();
        TiempoVidaToken response = new TiempoVidaToken();
        String codigoCorresponsal = "";
        String codigoTerminal = "";
        String hashMessage = "";
        String ipClienteCorresponsal = "";
        String ipServidorCorresponsal = "";
        String macClienteCorresponsal = "";
        String macServidorCorresponsal = "";
        String numeroReferencia = "";
        String paisOrigen = "";

        response.setTiempoVidaToken("60");
        Cabecera cabecera = new Cabecera();
     
        cabecera.setHashMessage(hashMessage);
        cabecera.setIpClienteCorresponsal(ipClienteCorresponsal);
        cabecera.setIpServidorCorresponsal(ipServidorCorresponsal);
        cabecera.setMacClienteCorresponsal(macClienteCorresponsal);
        cabecera.setMacServidorCorresponsal(macServidorCorresponsal);
        cabecera.setNumeroReferencia(numeroReferencia);
        cabecera.setPaisOrigen(paisOrigen);

        Mockito.when(otcTokenService.solicitarToken(any())).thenReturn("60");

        controller.obtenerOtcRetiro(request, cabecera, new DepositoID());
        Mockito.verify(otcTokenService, Mockito.times(1)).solicitarToken(any());
        assertEquals("60", response.getTiempoVidaToken(), "Tiempo de Vida no correcto");

    }

}