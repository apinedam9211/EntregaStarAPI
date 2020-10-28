package co.com.bcs.apibcs.dto.v1.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Saldo {

	@Schema(title = "Descripción de saldo de cliente")
	private String descripcion;

	@Schema(title = "Valor dos decimales sin comillas de saldo cliente")
	private String valor;
}