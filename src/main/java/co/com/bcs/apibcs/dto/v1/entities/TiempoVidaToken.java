package co.com.bcs.apibcs.dto.v1.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiempoVidaToken  {

	@Schema(title = "Tiempo de vida del token OTC generado")
    String tiempoVidaToken;

}