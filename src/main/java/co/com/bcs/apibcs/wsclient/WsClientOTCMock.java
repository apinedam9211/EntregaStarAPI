package co.com.bcs.apibcs.wsclient;

import java.math.BigInteger;

import co.com.bcs.backend.services.client.samotc.CabeceraSalida;
import co.com.bcs.backend.services.client.samotc.RespuestaError;
import co.com.bcs.backend.services.client.samotc.SolicitarOTCRetiroRequestType;
import co.com.bcs.backend.services.client.samotc.SolicitarOTCRetiroResponseType;

public class WsClientOTCMock implements IWSClientOTC {

    @Override
    public SolicitarOTCRetiroResponseType solicitarTokenOTC(SolicitarOTCRetiroRequestType request) {

        SolicitarOTCRetiroResponseType response = new SolicitarOTCRetiroResponseType();
        CabeceraSalida cabeceraSalida = new CabeceraSalida();
        if (validateRequest(request)) {
            cabeceraSalida.setTipoRespuesta("OK");
            response.setExpira(BigInteger.valueOf(60));
        } else {
            cabeceraSalida.setTipoRespuesta("ER");
            RespuestaError error = new RespuestaError();
            error.setCodigoError("codigoError");
            error.setDescripcionError("descripcionError");
            error.setTipoError("tipoError");
            cabeceraSalida.setRespuestaError(error);
        }
        response.setCabeceraSalida(cabeceraSalida);
        return response;
    }

    public boolean validateRequest(SolicitarOTCRetiroRequestType request) {
        if(!request.getCorreoElectronico().equals("L4R7F6O5@BANCOCAJASOCIAL.COM")){
            return false;
        }
        if(!request.getDatosRetiro().getCelular().equals("3142155862")){
            return false;
        }

        if(!request.getIdentificacion().getNumIdentificacion().equals("10283050")){
            return false;
        }
        
        return request.getIdentificacion().getTpIdentidicacion().equals("CC");
    }

}