package co.com.bcs.apibcs.wsclient;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import co.com.bcs.backend.services.client.samotc.ObjectFactory;
import co.com.bcs.backend.services.client.samotc.SolicitarOTCRetiroRequestType;
import co.com.bcs.backend.services.client.samotc.SolicitarOTCRetiroResponseType;

public interface IWSClientOTC  {

    
    public SolicitarOTCRetiroResponseType solicitarTokenOTC(SolicitarOTCRetiroRequestType request) ;
}