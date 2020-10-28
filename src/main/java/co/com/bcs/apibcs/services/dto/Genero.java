package co.com.bcs.apibcs.services.dto;

import lombok.Getter;

@Getter
public enum Genero {

	F("Femenino"), M("Masculino");

	private String descripcion;

	private Genero(String descripcion) {

		this.descripcion = descripcion;

	}

}
