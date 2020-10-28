package co.com.bcs.apibcs.services.dto;

import lombok.Data;

@Data
public class DebitoDepositoElectronicoRequest {

    private String referencia;

    private Identificacion cliente;

    private String monto;

    private String pinBlock;

    private Cabeceras cabecera;

}