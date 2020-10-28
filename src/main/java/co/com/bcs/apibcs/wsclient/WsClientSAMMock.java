package co.com.bcs.apibcs.wsclient;

import java.math.BigDecimal;
import java.math.BigInteger;

import co.com.bcs.backend.services.client.sam.CabeceraSalida;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaRequestType;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaResponseType;
import co.com.bcs.backend.services.client.sam.FinalizarRegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.FinalizarRegistroClienteResponseType;
import co.com.bcs.backend.services.client.sam.InfoMovimientoType;
import co.com.bcs.backend.services.client.sam.ListaMovimientosType;
import co.com.bcs.backend.services.client.sam.RegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.RegistroClienteResponseType;
import co.com.bcs.backend.services.client.sam.RespuestaError;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaRequestType;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaResponseType;

public class WsClientSAMMock implements WsClientSAM {

    @Override
    public ValidarAlcanciaResponseType validarDepositoElectronico(ValidarAlcanciaRequestType request) {
        ValidarAlcanciaResponseType response = new ValidarAlcanciaResponseType();
        response.setCabeceraSalida(cabeceraOK());
        response.setTieneAlcancia("SI");
        return response;
    }

    private CabeceraSalida cabeceraOK() {
        CabeceraSalida cabeceraSalida = new CabeceraSalida();
        cabeceraSalida.setTipoRespuesta("OK");
        return cabeceraSalida;
    }

    @Override
    public RegistroClienteResponseType solicitarDepositoElectronico(RegistroClienteRequestType request) {
        RegistroClienteResponseType response = new RegistroClienteResponseType();
        response.setCabeceraSalida(cabeceraOK());
        return response;
    }

    @Override
    public FinalizarRegistroClienteResponseType finalizarSolicitarDepositoElectronico(
            FinalizarRegistroClienteRequestType request) {
        FinalizarRegistroClienteResponseType response = new FinalizarRegistroClienteResponseType();
        response.setCabeceraSalida(cabeceraOK());
        return response;
    }

    @Override
    public ConsultarMovimientosAlcanciaResponseType consultarMovimientosAlcancia(
            ConsultarMovimientosAlcanciaRequestType request) {
        ConsultarMovimientosAlcanciaResponseType response = new ConsultarMovimientosAlcanciaResponseType();
        if (request.getPaginacion().getRegistroInicial().compareTo(BigInteger.valueOf(3)) > 0) {
            response.setCabeceraSalida(cabeceraError());
        } else {
            response.setCabeceraSalida(cabeceraOK());
            ListaMovimientosType listaMovimientos = new ListaMovimientosType();
            InfoMovimientoType movimiento = new InfoMovimientoType();
            movimiento.setDescripcion("descripcion Movimiento");
            movimiento.setFecha("20200515");
            movimiento.setValor(BigDecimal.TEN);
            listaMovimientos.getMovimientos().add(movimiento);
            response.setListaMovimientos(listaMovimientos);
            response.setSaldo(BigDecimal.TEN);
        }
        return response;
    }

    private CabeceraSalida cabeceraError() {
        CabeceraSalida cabeceraSalida = new CabeceraSalida();
        cabeceraSalida.setTipoRespuesta("ER");
        RespuestaError respuestaError = new RespuestaError();
        respuestaError.setCodigoError("codigoError");
        respuestaError.setDescripcionError("descripcionError");
        respuestaError.setTipoError("tipoError");
        cabeceraSalida.setRespuestaError(respuestaError);
        return cabeceraSalida;
    }
}
