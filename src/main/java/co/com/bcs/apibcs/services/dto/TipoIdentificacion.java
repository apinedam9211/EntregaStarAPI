package co.com.bcs.apibcs.services.dto;

import lombok.Getter;

@Getter
public enum TipoIdentificacion {

CC("Cedula de Ciudadania");


private String descripcion;

private TipoIdentificacion(String descripcion) {
    this.descripcion = descripcion;
}

}
