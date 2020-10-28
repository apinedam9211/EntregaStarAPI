package co.com.bcs.apibcs.tuxedo.objects;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class SolicitudMedioManejoRequest implements TuxedoInterfaceRequest{

    CabeceraStarOnline cabecera;
    String tipoID;
    String id;
    String numCuenta;
    String tipoProceso;
    String numeroTarjetaAnterior; 
    String codigoRazonCambio;
    String tipoEntrega;
    String canalSolicitud;
    String identificadorAliado;
    boolean cobroTarjeta;
    boolean cobroServicio;
    String direccionEntrega;
    String ciudadEntrega;
    String nombrePais;
    String codigoPostal;
    String correoElectronico;





    @Override
    public String toTrama() {
        return cabecera.toTrama() + StringUtils.rightPad(StringUtils.defaultString(tipoID),2) + 
        StringUtils.rightPad(StringUtils.defaultString(id),18) + 
        StringUtils.rightPad(StringUtils.defaultString(numCuenta),20) + 
        StringUtils.rightPad(StringUtils.defaultString(tipoProceso),1) + 
        StringUtils.rightPad(StringUtils.defaultString(numeroTarjetaAnterior),19) + 
        StringUtils.rightPad(StringUtils.defaultString(codigoRazonCambio),2) + 
        StringUtils.rightPad(StringUtils.defaultString(tipoEntrega),3) + 
        StringUtils.rightPad(StringUtils.defaultString(canalSolicitud),3) + 
        StringUtils.rightPad(StringUtils.defaultString(identificadorAliado),50) + 
        StringUtils.rightPad(cobroTarjeta ? "S" : "N" , 1) + 
        StringUtils.rightPad(cobroServicio? "S" : "N", 1) + 
        StringUtils.rightPad(StringUtils.defaultString(direccionEntrega),50) + 
        StringUtils.rightPad(StringUtils.defaultString(ciudadEntrega),50) + 
        StringUtils.rightPad(StringUtils.defaultString(nombrePais),20) + 
        StringUtils.rightPad(StringUtils.defaultString(codigoPostal),10) + 
        StringUtils.rightPad(StringUtils.defaultString(correoElectronico),60);
    }
    
}