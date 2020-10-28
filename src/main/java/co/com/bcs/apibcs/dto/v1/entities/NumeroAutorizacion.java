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
public class NumeroAutorizacion {
    
	@Schema(title = "Número de autorización de transacción")
    private String numeroAutorizacion;

}