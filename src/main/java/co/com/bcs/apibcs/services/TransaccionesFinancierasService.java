package co.com.bcs.apibcs.services;

import co.com.bcs.apibcs.services.dto.CreditoDepositoElectronicoRequest;
import co.com.bcs.apibcs.services.dto.DebitoDepositoElectronicoRequest;

public interface TransaccionesFinancierasService {

    public String retiro(DebitoDepositoElectronicoRequest request);

    public String credito(CreditoDepositoElectronicoRequest request);

}