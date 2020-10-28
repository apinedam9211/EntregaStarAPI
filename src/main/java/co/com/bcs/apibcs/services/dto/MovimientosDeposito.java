package co.com.bcs.apibcs.services.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovimientosDeposito {

    private String fecha;
    private String descripcion;
    private BigDecimal valor;


}
