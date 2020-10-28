package co.com.bcs.apibcs.tuxedo.objects;

import lombok.Data;

@Data
public abstract class TuxedoResponse {

    private CabeceraStarOnlineSalida salida;
    private String string;

    public boolean isError() {
        return (salida != null && "ER".equals(salida.getStatus()));
    }

    public TuxedoResponse(String response) {

        this.string = response;
        this.salida = new CabeceraStarOnlineSalida(response);
        if (!salida.isError()) {
            llenarParametros();
        }
    }

    protected abstract void llenarParametros();

}