package co.com.bcs.apibcs.wsclient;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESRequest;
import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESResponse;
import co.com.bcs.backend.services.client.encriptacion.ObjectFactory;

public class WsClientEncriptacionImpl extends WebServiceGatewaySupport implements WsClientEncriptacion{

    private String actionEncriptar = "http://xmlns.bancocajasocial.com/co/servicios/EncriptacionMensajeAES/v1.0/encriptarMensajeAES";

    public EncriptarMensajeAESResponse encriptarMensaje(EncriptarMensajeAESRequest request) {

        JAXBElement<EncriptarMensajeAESRequest> requestJAXB = new ObjectFactory()
                .createEncriptarMensajeAESRequest(request);
        JAXBElement<EncriptarMensajeAESResponse> response = (JAXBElement<EncriptarMensajeAESResponse>) this
                .getWebServiceTemplate().marshalSendAndReceive(requestJAXB, new SoapActionCallback(actionEncriptar));
        return response.getValue();

    }

}