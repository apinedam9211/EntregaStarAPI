package co.com.bcs.apibcs.wsclient;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import co.com.bcs.backend.services.client.samotc.ObjectFactory;
import co.com.bcs.backend.services.client.samotc.SolicitarOTCRetiroRequestType;
import co.com.bcs.backend.services.client.samotc.SolicitarOTCRetiroResponseType;

public class WSClientOTCImpl extends WebServiceGatewaySupport implements IWSClientOTC {

    private String actionSolicitarOTC = "http://xmlns.bancocajasocial.com/co/canales/servicios/oneTimePassword/v1.0/SolicitarOTCRetiro";

    public SolicitarOTCRetiroResponseType solicitarTokenOTC(SolicitarOTCRetiroRequestType request) {

        JAXBElement<SolicitarOTCRetiroRequestType> requestJAXB = new ObjectFactory()
                .createSolicitarOTCRetiroRequest(request);

      JAXBElement<SolicitarOTCRetiroResponseType> response =  (JAXBElement<SolicitarOTCRetiroResponseType>) this
              .getWebServiceTemplate().marshalSendAndReceive(requestJAXB, new SoapActionCallback(actionSolicitarOTC));

      return response.getValue();
    }

}