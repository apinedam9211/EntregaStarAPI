package co.com.bcs.apibcs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import co.com.bcs.apibcs.wsclient.IWSClientOTC;
import co.com.bcs.apibcs.wsclient.WsClientEncriptacion;
import co.com.bcs.apibcs.wsclient.WsClientEncriptacionMock;
import co.com.bcs.apibcs.wsclient.WsClientOTCMock;
import co.com.bcs.apibcs.wsclient.WsClientSAM;
import co.com.bcs.apibcs.wsclient.WsClientSAMMock;

@Configuration
@Profile("tomcat")
public class WsClientConfigMemory {

    @Value("${ws.client.encripcion.url}")
    String urlWSEncriptacion;

    @Bean
    public IWSClientOTC otcBeanMock() {
        return new WsClientOTCMock();
    }

    @Bean
    public WsClientSAM operacionesSAM() {
        return new WsClientSAMMock();
    }

    @Bean
    public WsClientEncriptacion encriptacionWSClient() {
        return new WsClientEncriptacionMock();
    }

}