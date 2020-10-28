package co.com.bcs.apibcs.services.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultarSaldoDepositoRequest {

	private Cabeceras cabecera;
	private Identificacion cliente;

	private String celular;
}