package co.com.bcs.apibcs.wsclient;

import co.com.bcs.backend.services.client.encriptacion.CabeceraSalida;
import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESRequest;
import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESResponse;

public class WsClientEncriptacionMock implements WsClientEncriptacion {

    @Override
    public EncriptarMensajeAESResponse encriptarMensaje(EncriptarMensajeAESRequest request) {
        EncriptarMensajeAESResponse response = new EncriptarMensajeAESResponse();
        response.setEncriptacionAES("encriptacionAES");
        CabeceraSalida cabeceraSalida = new CabeceraSalida();
        cabeceraSalida.setTipoRespuesta("OK");
        response.setCabeceraSalida(cabeceraSalida);
        return response;
    }
    
}