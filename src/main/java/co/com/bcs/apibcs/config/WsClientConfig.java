package co.com.bcs.apibcs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

import co.com.bcs.apibcs.interceptor.LoggingClientWSInterceptor;
import co.com.bcs.apibcs.wsclient.IWSClientOTC;
import co.com.bcs.apibcs.wsclient.WSClientOTCImpl;
import co.com.bcs.apibcs.wsclient.WsClientEncriptacion;
import co.com.bcs.apibcs.wsclient.WsClientEncriptacionImpl;
import co.com.bcs.apibcs.wsclient.WsClientOTCMock;
import co.com.bcs.apibcs.wsclient.WsClientSAM;
import co.com.bcs.apibcs.wsclient.WsClientSAMImpl;

@Configuration
@Profile("weblogic")
public class WsClientConfig {

	@Value("${ws.client.sam.url}")
	private String urlWSSAM;

	@Value("${ws.client.otc.url}")
	private String urlOTC;

	@Value("${ws.client.encripcion.url}")
	String urlWSEncriptacion;

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("co.com.bcs.backend.services.client.sam");
		return marshaller;
	}

	@Bean
	@Profile("weblogic")
	public IWSClientOTC otcBean() {
		WSClientOTCImpl wsClientOTC = new WSClientOTCImpl();
		wsClientOTC.setDefaultUri(urlOTC);
		wsClientOTC.setMarshaller(otcMarshaller());
		wsClientOTC.setUnmarshaller(otcMarshaller());
		final ClientInterceptor[] interceptors = new ClientInterceptor[] { this.loggingClientWSInterceptor() };
		wsClientOTC.setInterceptors(interceptors);
		return wsClientOTC;

	}

	private Jaxb2Marshaller otcMarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("co.com.bcs.backend.services.client.samotc");
		return marshaller;
	}

	@Bean
	public WsClientSAM operacionesSAM() {
		WsClientSAMImpl wsClient = new WsClientSAMImpl();
		wsClient.setDefaultUri(urlWSSAM);
		wsClient.setMarshaller(marshaller());
		wsClient.setUnmarshaller(marshaller());

		final ClientInterceptor[] interceptors = new ClientInterceptor[] { this.loggingClientWSInterceptor() };
		wsClient.setInterceptors(interceptors);

		return wsClient;
	}

	@Bean
	public WsClientEncriptacion encriptacionWSClient() {

		WsClientEncriptacionImpl wsClient = new WsClientEncriptacionImpl();

		wsClient.setDefaultUri(urlWSEncriptacion);
		wsClient.setMarshaller(marshallerEncriptacion());
		wsClient.setUnmarshaller(marshallerEncriptacion());

		final ClientInterceptor[] interceptors = new ClientInterceptor[] { this.loggingClientWSInterceptor() };
		wsClient.setInterceptors(interceptors);

		return wsClient;
	}

	private Jaxb2Marshaller marshallerEncriptacion() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("co.com.bcs.backend.services.client.encriptacion");
		return marshaller;
	}

	@Bean
	public LoggingClientWSInterceptor loggingClientWSInterceptor() {

		return new LoggingClientWSInterceptor();
	}

}