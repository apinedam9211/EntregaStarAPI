package co.com.bcs.apibcs.services.dto;

import lombok.Data;

@Data
public class OTCRetiroRequest {

	private Cabeceras cabecera;

	private Identificacion identificacionCliente;

	private String correo;

	private String celular;

	private String valorRetiro;

}