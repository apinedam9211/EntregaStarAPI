package co.com.bcs.apibcs.wsclient;

import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaRequestType;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaResponseType;
import co.com.bcs.backend.services.client.sam.FinalizarRegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.FinalizarRegistroClienteResponseType;
import co.com.bcs.backend.services.client.sam.RegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.RegistroClienteResponseType;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaRequestType;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaResponseType;

public interface WsClientSAM {

	public ValidarAlcanciaResponseType validarDepositoElectronico(ValidarAlcanciaRequestType request);

	public RegistroClienteResponseType solicitarDepositoElectronico(RegistroClienteRequestType request);

	public FinalizarRegistroClienteResponseType finalizarSolicitarDepositoElectronico(
			FinalizarRegistroClienteRequestType request);

	public ConsultarMovimientosAlcanciaResponseType consultarMovimientosAlcancia(
			ConsultarMovimientosAlcanciaRequestType request);

}