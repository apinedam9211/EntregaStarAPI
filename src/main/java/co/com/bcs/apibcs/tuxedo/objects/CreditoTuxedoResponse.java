package co.com.bcs.apibcs.tuxedo.objects;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class CreditoTuxedoResponse extends TuxedoResponse {

    private String numeroAutorizacion;
    private String codigoRespuesta;
    private String nombreTitular;
    private String saldoPDS;
    private String ganancia;
    private String comision;
    private String numeroCuenta;

    public CreditoTuxedoResponse(String string) {
        super(string);
    }

    @Override
    protected void llenarParametros() {

        this.setNumeroAutorizacion(StringUtils.substring(this.getString(), 70, 76));
        this.setCodigoRespuesta(StringUtils.substring(this.getString(), 76, 78));
        this.setNombreTitular(StringUtils.substring(this.getString(), 78, 106));
        this.setSaldoPDS(StringUtils.substring(this.getString(), 106, 118));
        this.setGanancia(StringUtils.substring(this.getString(), 118, 130));
        this.setComision(StringUtils.substring(this.getString(), 130, 142));
        this.setNumeroCuenta(StringUtils.substring(this.getString(), 142, 170));

    }
}