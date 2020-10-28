package co.com.bcs.apibcs.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingClientWSInterceptor implements ClientInterceptor {

    @Override
    public void afterCompletion(MessageContext messageContext, Exception arg2) throws WebServiceClientException {
 // Nothing
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {

        return true;
    }

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        final WebServiceMessage request = messageContext.getRequest();
        final SoapMessage soapMessage = (SoapMessage) request;

        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            soapMessage.writeTo(baos);
            log.error("request" + baos.toString());
        } catch (final IOException e) {
            log.error("problema al obtener request", e);
        }

        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        final WebServiceMessage response = messageContext.getResponse();
        final SoapMessage soapMessage = (SoapMessage) response;

        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            soapMessage.writeTo(baos);
            log.error("response" + baos.toString());
        } catch (final IOException e) {
            log.error("problema al obtener request", e);
        }

        return true;
    }

}