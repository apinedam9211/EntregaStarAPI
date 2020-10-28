package co.com.bcs.apibcs.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidarDepositoRequest {

	private Cabeceras cabecera;

	private Identificacion identificacion;
	
	private String correo;

	private String celular;

}