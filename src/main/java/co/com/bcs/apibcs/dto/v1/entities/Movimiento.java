package co.com.bcs.apibcs.dto.v1.entities;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {

	@Schema(title = "Fecha del movimiento financiero")
	private String fecha;

	@Schema(title = "Descripción del movimiento")
	private String descripcion;

	@Schema(title = "Valor de movimiento con dos decimales sin comillas")
	private BigDecimal valor;

}