package co.com.bcs.apibcs.tuxedo.objects;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

@Getter
public class RetiroTuxedoResponse extends TuxedoResponse {

    private String numeroAutorizacion;
    private String codigoRespuesta;
    private String valorDisponible;
    private String valorComision;
    private String numeroCuenta;

    public RetiroTuxedoResponse(String response) {
        super(response);
    }

    @Override
    protected void llenarParametros() {
        this.numeroAutorizacion = (StringUtils.substring(this.getString(), 70, 76));
        this.codigoRespuesta = (StringUtils.substring(this.getString(), 76, 78));
        this.valorDisponible = (StringUtils.substring(this.getString(), 78, 90));
        this.valorComision = (StringUtils.substring(this.getString(), 102, 114));
        this.numeroCuenta = (StringUtils.substring(this.getString(), 114, 134));
    }

}