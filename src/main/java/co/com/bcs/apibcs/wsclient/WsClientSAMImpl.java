package co.com.bcs.apibcs.wsclient;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaRequestType;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaResponseType;
import co.com.bcs.backend.services.client.sam.FinalizarRegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.FinalizarRegistroClienteResponseType;
import co.com.bcs.backend.services.client.sam.ObjectFactory;
import co.com.bcs.backend.services.client.sam.RegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.RegistroClienteResponseType;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaRequestType;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaResponseType;

public class WsClientSAMImpl  extends WebServiceGatewaySupport implements WsClientSAM {

	private String actionValidarAlcancia = "http://xmlns.bancocajasocial.com/co/servicios/ClienteSAM/v1.0/validarAlcancia";
	private String actionRegistrarCliente = "http://xmlns.bancocajasocial.com/co/servicios/ClienteSAM/v1.0/registrarCliente";
	private String actionFinRegistroCliente = "http://xmlns.bancocajasocial.com/co/servicios/ClienteSAM/v1.0/finalizarRegistroCliente";
	private String actionConsultarMovimientos = "http://xmlns.bancocajasocial.com/co/servicios/ClienteSAM/v1.0/consultarMovimientosAlcancia";

	public ValidarAlcanciaResponseType validarDepositoElectronico(ValidarAlcanciaRequestType request) {

		JAXBElement<ValidarAlcanciaResponseType> validarResponse = (JAXBElement<ValidarAlcanciaResponseType>) this
				.getWebServiceTemplate()
				.marshalSendAndReceive(new ObjectFactory().createValidarAlcanciaRequest(request),
						new SoapActionCallback(actionValidarAlcancia));
		return validarResponse.getValue();
	}

	public RegistroClienteResponseType solicitarDepositoElectronico(RegistroClienteRequestType request) {
		JAXBElement<RegistroClienteResponseType> registroResponse = (JAXBElement<RegistroClienteResponseType>) this
				.getWebServiceTemplate()
				.marshalSendAndReceive(new ObjectFactory().createRegistrarClienteRequest(request),
						(WebServiceMessageCallback) new SoapActionCallback(actionRegistrarCliente));
		return registroResponse.getValue();
	}

	public FinalizarRegistroClienteResponseType finalizarSolicitarDepositoElectronico(
			FinalizarRegistroClienteRequestType request) {
		JAXBElement<FinalizarRegistroClienteResponseType> registroResponse = (JAXBElement<FinalizarRegistroClienteResponseType>) this
				.getWebServiceTemplate()
				.marshalSendAndReceive(new ObjectFactory().createFinalizarRegistroClienteRequest(request),
						(WebServiceMessageCallback) new SoapActionCallback(actionFinRegistroCliente));
		return registroResponse.getValue();
	}

	public ConsultarMovimientosAlcanciaResponseType consultarMovimientosAlcancia(
			ConsultarMovimientosAlcanciaRequestType request) {
		JAXBElement<ConsultarMovimientosAlcanciaResponseType> registroResponse = (JAXBElement<ConsultarMovimientosAlcanciaResponseType>) this
				.getWebServiceTemplate()
				.marshalSendAndReceive(new ObjectFactory().createConsultarMovimientosAlcanciaRequest(request),
						(WebServiceMessageCallback) new SoapActionCallback(actionConsultarMovimientos));

		return registroResponse.getValue();
	}

}