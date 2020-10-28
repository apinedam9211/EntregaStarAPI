package co.com.bcs.apibcs.services;



import co.com.bcs.apibcs.services.dto.SolicitarDeposito;
import co.com.bcs.apibcs.services.dto.ValidarDepositoRequest;
import co.com.bcs.apibcs.services.dto.ConsultarMovimientosDepositoRequest;
import co.com.bcs.apibcs.services.dto.ConsultarMovimientosDepositoResponse;
import co.com.bcs.apibcs.services.dto.ConsultarSaldoDepositoRequest;

public interface AlcanciaAmigaService {
    
     public boolean validarDeposito (ValidarDepositoRequest request);

     public void solicitarDeposito(SolicitarDeposito request);
     
     public ConsultarMovimientosDepositoResponse consultarMovimientos(ConsultarMovimientosDepositoRequest request);

     public String consultarSaldo(ConsultarSaldoDepositoRequest request);

}