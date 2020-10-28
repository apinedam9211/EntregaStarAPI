package co.com.bcs.apibcs.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

import co.com.bcs.apibcs.exception.BackendException;
import co.com.bcs.apibcs.exception.Error500Exception;
import co.com.bcs.apibcs.wsclient.IWSClientOTC;
import co.com.bcs.apibcs.wsclient.WsClientEncriptacion;
import co.com.bcs.apibcs.wsclient.WsClientSAM;
import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESRequest;
import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESResponse;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaRequestType;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaResponseType;
import co.com.bcs.backend.services.client.sam.FinalizarRegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.FinalizarRegistroClienteResponseType;
import co.com.bcs.backend.services.client.sam.RegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.RegistroClienteResponseType;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaRequestType;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaResponseType;
import co.com.bcs.backend.services.client.samotc.SolicitarOTCRetiroRequestType;
import co.com.bcs.backend.services.client.samotc.SolicitarOTCRetiroResponseType;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WsClientServiceImpl implements WsClientService {

    private IWSClientOTC clientOTC;

    private WsClientEncriptacion clientEncriptacion;

    private WsClientSAM clientSAM;

    private static final String GET_CABECERA_SALIDA = "getCabeceraSalida";

    private static final String GET_TIPO_RESPUESTA = "getTipoRespuesta";

    public SolicitarOTCRetiroResponseType solicitarOTCRetiro(SolicitarOTCRetiroRequestType request) {

        SolicitarOTCRetiroResponseType response = clientOTC.solicitarTokenOTC(request);
        validarRespuestaOK(response);
        return response;
    }

    public ValidarAlcanciaResponseType validarDepositoElectronico(ValidarAlcanciaRequestType request) {

        ValidarAlcanciaResponseType response = clientSAM.validarDepositoElectronico(request);
        validarRespuestaOK(response);
        return response;

    }

    public RegistroClienteResponseType solicitarDepositoElectronico(RegistroClienteRequestType request) {
        RegistroClienteResponseType response = clientSAM.solicitarDepositoElectronico(request);
        validarRespuestaOK(response);
        return response;
    }

    public FinalizarRegistroClienteResponseType finalizarSolicitarDepositoElectronico(
            FinalizarRegistroClienteRequestType request) {
        FinalizarRegistroClienteResponseType response = clientSAM.finalizarSolicitarDepositoElectronico(request);
        validarRespuestaOK(response);
        return response;
    }

    public ConsultarMovimientosAlcanciaResponseType consultarMovimientosAlcancia(
            ConsultarMovimientosAlcanciaRequestType request) {
        ConsultarMovimientosAlcanciaResponseType response = clientSAM.consultarMovimientosAlcancia(request);

        validarRespuestaOK(response);
        return response;
    }

    public EncriptarMensajeAESResponse encriptarMensaje(EncriptarMensajeAESRequest request) {
        EncriptarMensajeAESResponse response = clientEncriptacion.encriptarMensaje(request);
        validarRespuestaOK(response);
        return response;
    }

    private void validarRespuestaOK(Object respuesta) {

        try {
            Method getCabeceraSalida = respuesta.getClass().getMethod(GET_CABECERA_SALIDA);
            Object cabeceraSalida = getCabeceraSalida.invoke(respuesta);
            Method getTipoRespuesta = cabeceraSalida.getClass().getMethod(GET_TIPO_RESPUESTA);
            String tipoRespuesta = (String) getTipoRespuesta.invoke(cabeceraSalida);

            if (!"OK".equalsIgnoreCase(tipoRespuesta)) {

                Method getRespuestaError = cabeceraSalida.getClass().getMethod("getRespuestaError");
                Object respuestaError = getRespuestaError.invoke(cabeceraSalida);
                Method getCodigoError = respuestaError.getClass().getMethod("getCodigoError");
                String codigoError = (String) getCodigoError.invoke(respuestaError);
                Method getDescripcionError = respuestaError.getClass().getMethod("getDescripcionError");
                String descripcionError = (String) getDescripcionError.invoke(respuestaError);
                Method getTipoError = respuestaError.getClass().getMethod("getTipoError");
                String tipoError = (String) getTipoError.invoke(respuestaError);
                // calcular error
                throw new BackendException(descripcionError, tipoError + codigoError, descripcionError);
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new Error500Exception(e.getMessage(), "500", "En este momento no podemos atenderlo");
        }

    }

}