package co.com.bcs.apibcs.dto.v1;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ApiBCSServer {
	
	private String tipoId;
	
	private String numeroId;
	
	private String numeroReferencia;
	
	private Timestamp fecha;
	
	private String origen;
	
	private String metodo;
	
	private String estado;
	
	private String request;
	
	private String response;
	
	private String mensajeError;
}
