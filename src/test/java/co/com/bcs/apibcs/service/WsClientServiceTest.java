package co.com.bcs.apibcs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.bcs.apibcs.exception.BackendException;
import co.com.bcs.apibcs.services.WsClientService;
import co.com.bcs.apibcs.services.WsClientServiceImpl;
import co.com.bcs.apibcs.wsclient.WSClientOTCImpl;
import co.com.bcs.apibcs.wsclient.WsClientEncriptacion;
import co.com.bcs.apibcs.wsclient.WsClientSAM;
import co.com.bcs.backend.services.client.encriptacion.CabeceraSalida;
import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESRequest;
import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESResponse;
import co.com.bcs.backend.services.client.encriptacion.RespuestaError;

public class WsClientServiceTest {

    private WsClientService clientService;
    @Mock
    private WSClientOTCImpl clientOTC;
    @Mock
    private WsClientEncriptacion clientEncriptacion;
    @Mock
    private WsClientSAM clientSAM;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        clientService = new WsClientServiceImpl(clientOTC, clientEncriptacion, clientSAM);
    }

    @Test
    void testBackendException() {

        EncriptarMensajeAESRequest request = new EncriptarMensajeAESRequest();

        EncriptarMensajeAESResponse response = createResponseError();
        when(clientEncriptacion.encriptarMensaje(request)).thenReturn(response);
        assertThrows(BackendException.class, () -> clientService.encriptarMensaje(request));

        verify(clientEncriptacion, times(1)).encriptarMensaje(request);

    }

    @Test
    void consumoOKEncriptar() {

        EncriptarMensajeAESRequest request = new EncriptarMensajeAESRequest();

        EncriptarMensajeAESResponse response = createResponseOK();
        when(clientEncriptacion.encriptarMensaje(request)).thenReturn(response);
        EncriptarMensajeAESResponse result = clientService.encriptarMensaje(request);

        assertEquals(response.getEncriptacionAES(), result.getEncriptacionAES());

        verify(clientEncriptacion, times(1)).encriptarMensaje(request);

    }

    private EncriptarMensajeAESResponse createResponseError() {
        EncriptarMensajeAESResponse error = new EncriptarMensajeAESResponse();
        CabeceraSalida cabeceraSalida = new CabeceraSalida();
        RespuestaError respuestaError = new RespuestaError();
        respuestaError.setCodigoError("codigoError");
        respuestaError.setTipoError("tipoError");
        respuestaError.setDescripcionError("descripcionError");
        cabeceraSalida.setRespuestaError(respuestaError);
        cabeceraSalida.setTipoRespuesta("ER");
        error.setCabeceraSalida(cabeceraSalida);

        return error;
    }

    private EncriptarMensajeAESResponse createResponseOK() {
        EncriptarMensajeAESResponse ok = new EncriptarMensajeAESResponse();
        CabeceraSalida cabeceraSalida = new CabeceraSalida();
        cabeceraSalida.setTipoRespuesta("OK");
        ok.setEncriptacionAES("123456");
        ok.setCabeceraSalida(cabeceraSalida);

        return ok;

    }
}