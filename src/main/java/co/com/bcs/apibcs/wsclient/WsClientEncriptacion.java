package co.com.bcs.apibcs.wsclient;

import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESRequest;
import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESResponse;

public interface WsClientEncriptacion {

	public EncriptarMensajeAESResponse encriptarMensaje(EncriptarMensajeAESRequest request);
}