package co.com.bcs.apibcs.services.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ConsultarMovimientosDepositoResponse {

    private List<MovimientosDeposito> movimientos;

    public List<MovimientosDeposito> getMovimientos() {

        if (movimientos == null) {
            movimientos = new ArrayList<>();
        }
        return movimientos;
    }

}