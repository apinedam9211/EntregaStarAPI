package co.com.bcs.apibcs.services.dto;


import lombok.Data;

@Data
public class CreditoDepositoElectronicoRequest {
  
	private Cabeceras cabecera; 
	private String referencia;
   
	private Identificacion cliente;

	private String monto;

}