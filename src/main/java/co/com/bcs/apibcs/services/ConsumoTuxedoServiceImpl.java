package co.com.bcs.apibcs.services;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.management.ServiceNotFoundException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.stereotype.Service;

import weblogic.wtc.gwt.TuxedoConnection;
import weblogic.wtc.gwt.TuxedoConnectionFactory;
import weblogic.wtc.jatmi.Reply;
import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TypedCArray;

@Slf4j
@Service
public class ConsumoTuxedoServiceImpl implements ConsumoTuxedoService {
    
    static final String TUX_DEAL_JNDI = "tuxedo.services.TuxedoConnection";

    private String extractResponse(Reply reply) throws UnsupportedEncodingException {
        if (reply == null) {
            return null;
        }
        Object object = reply.getReplyBuffer();
        if (object == null) {
            return null;
        }
        if (!(object instanceof TypedCArray)) {
            log.warn("NO se reconoce respuesta tuxedo tipo " + object.getClass().getName() + " Se espera CARRAY");
            return null;
        }
        TypedCArray typedArray = (TypedCArray) object;
        String response = "";

        if (typedArray != null && typedArray.carray != null) {
            int j;
            for (j = typedArray.carray.length - 1; j >= 0; j--) {
                if (typedArray.carray[j] != 0) {
                    break;
                }
            }
            if (j >= 0) {
                
                byte[] valido = new byte[j + 1];                
                for (int i = 0; i <= j; i++) {
                    valido[i] = typedArray.carray[i];
                }
                
                response = new String(valido,StandardCharsets.ISO_8859_1);
            }
        }
        return response;
    }

    public String invoke(String service, String payload) throws Exception {
        TypedCArray typedArray = new TypedCArray();
        Reply reply = null;
        TuxedoConnectionFactory tuxedoConnectionFactory = null;
        TuxedoConnection tuxedoConnection = null;

        try {
            InitialContext context = new InitialContext();
            tuxedoConnectionFactory = (TuxedoConnectionFactory) context.lookup(TUX_DEAL_JNDI);
            tuxedoConnection = tuxedoConnectionFactory.getTuxedoConnection();
        } catch (NamingException e) {
            log.info("NamingException en el metodo invoke: " + e.toString() , e);
            throw new Exception("Error al localizar TuxedoConnectionFactory en JNDI " + TUX_DEAL_JNDI +
                                " (esta bien configurado el WTC?): " + e.toString(), e);
        } catch (TPException e) {
            log.info("TPException en el metodo invoke: " + e.toString() , e);
            throw new Exception("Error al realizar conexion con el dominio tuxedo via WTC (esta bien configurado el WTC? �esta funcionando el dominio tuxedo?): " +
                                e.toString(), e);
        }

        try {
            log.info("ConexionTuxedo.payload: " + payload);
            int len = payload.length();
            log.info("len: " + len);
            byte[] bs = new byte[len];            
            System.arraycopy(payload.getBytes(StandardCharsets.ISO_8859_1), 0, bs, 0, payload.length());
            typedArray.carray = bs;
            typedArray.setSendSize(bs.length);

            reply = tuxedoConnection.tpcall(service, typedArray, 0);

            String response = extractResponse(reply);
            log.info("ConexionTuxedo.response: " + response);
            if (response == null) {
                log.warn("Respuesta nula del servicion o tipo de buffer inesperado");
                response = ""; // CADENA VACIA
            }
            return response;
        } catch (TPException exception) {
            log.error("TPException: " + service + ": " + exception.toString() , exception);
            switch (exception.gettperrno()) {
            case TPException.TPENOENT:
                throw new ServiceNotFoundException(service);
            case TPException.TPESVCFAIL:
                String r = extractResponse(exception.getReplyRtn());
                if (r == null) {
                    r = exception.toString();
                }
                throw new Exception(r);
            default:
                log.error("Fallo al solicitar servicio tuxedo " + service + ": " + exception.toString(), exception);
                throw new Exception(exception.toString());
            }
        } catch (Exception exception) {
            log.debug("ERROR DURANTE LA INVOCACION AL SERVICIO TUXEDO: " + exception.getMessage() + " - servicio " +
                         service + " PAYLOAD: " + payload, exception);
            throw new Exception("Error durante la invocacion al servicio " + service + ": " + exception.toString(),
                                exception);
        } finally {
            if (tuxedoConnection != null) {
                tuxedoConnection.tpterm();
            }
        }
    }



}