package co.com.bcs.apibcs.services.dto;

import lombok.Data;

@Data
public class SolicitarDeposito {

	private Cabeceras cabecera;
	private Identificacion identificacionCliente;

	private String primerNombre;

	private String segundoNombre;

	private String celular;

	private String primerApellido;

	private String segundoApellido;

	private String correoElectronico;

	private String fechaNacimiento;

	private Genero genero;

	private String fechaExpedicionDocumento;

	private String clave;

}