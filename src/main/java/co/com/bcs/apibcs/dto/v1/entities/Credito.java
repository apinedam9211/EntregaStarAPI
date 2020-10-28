package co.com.bcs.apibcs.dto.v1.entities;

import co.com.bcs.apibcs.validators.ConstraintStringAsNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Credito {
    

    @Schema(title= "Descripción de referencia de credito")
	@ConstraintStringAsNumber(maxSize = 12 , message = "{referencia.noValid}")
	public String referencia;

	@Schema(title= "Monto a retirar con dos decimales sin comillas")
	@ConstraintStringAsNumber(maxSize = 12, message = "{monto.isNumeric}")
	public String monto;


}