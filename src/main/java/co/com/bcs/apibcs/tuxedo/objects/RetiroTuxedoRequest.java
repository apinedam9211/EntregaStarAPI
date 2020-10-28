package co.com.bcs.apibcs.tuxedo.objects;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetiroTuxedoRequest implements TuxedoInterfaceRequest {

    private String canal;
    private String subcanal;
    private String numeroProducto;
    private String tipoTransaccion;
    private String codigoProcesamiento;
    private String valorTransaccion;
    private String numeroOriginador;
    private String hora;
    private String fechaPosteo;
    private String codigoEntidadAsociada;
    private String otroParametro;
    private String datosRastreo;
    private String referencia;
    private String idTerminal;
    private String puntoServicio;
    private String codigoSitio;
    private String pin;
    private String datosTerminal;
    private String cardIssuer;
    private String tokenQM;
    private String codigoInstitucion;

    @Override
    public String toTrama() {
        return StringUtils.leftPad(StringUtils.defaultString(canal.toString()), 2)
                + StringUtils.leftPad(StringUtils.defaultString(subcanal), 2)
                + StringUtils.rightPad(StringUtils.defaultString(numeroProducto), 19)
                + StringUtils.leftPad(StringUtils.defaultString(tipoTransaccion), 4)
                + StringUtils.leftPad(StringUtils.defaultString(codigoProcesamiento), 6)
                + StringUtils.leftPad(StringUtils.defaultString(valorTransaccion), 12 , "0")
                + StringUtils.leftPad(StringUtils.defaultString(numeroOriginador), 6 , "0")
                + StringUtils.leftPad(StringUtils.defaultString(hora), 6 , "0")
                + StringUtils.leftPad(StringUtils.defaultString(fechaPosteo), 4, "0")
                + StringUtils.leftPad(StringUtils.defaultString(codigoEntidadAsociada), 13)
                + StringUtils.leftPad(StringUtils.defaultString(otroParametro), 13)
                + StringUtils.leftPad(StringUtils.defaultString(datosRastreo), 39)
                + StringUtils.leftPad(StringUtils.defaultString(referencia), 12 , "0")
                + StringUtils.rightPad(StringUtils.defaultString(idTerminal), 16)
                + StringUtils.rightPad(StringUtils.defaultString(puntoServicio), 15)
                + StringUtils.leftPad(StringUtils.defaultString(codigoSitio), 40)
                + StringUtils.leftPad(StringUtils.defaultString(pin), 16)
                + StringUtils.leftPad(StringUtils.defaultString(datosTerminal), 15)
                + StringUtils.leftPad(StringUtils.defaultString(cardIssuer), 16)
                + StringUtils.rightPad(StringUtils.defaultString(tokenQM), 6)
                + StringUtils.leftPad(StringUtils.defaultString(codigoInstitucion), 11);
    }
}