package co.com.bcs.apibcs.services.dto;

import lombok.Data;

@Data
public class Cabeceras {

    private String codigoCorresponsal;

    private String codigoTerminal;

	private String numeroReferencia;

	private String paisOrigen;

	private String ipServidorCorresponsal;

	private String macServidorCorresponsal;

	private String ipClienteCorresponsal;
	
	private String macClienteCorresponsal;

}
