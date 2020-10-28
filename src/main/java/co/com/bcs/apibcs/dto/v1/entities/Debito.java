package co.com.bcs.apibcs.dto.v1.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import co.com.bcs.apibcs.validators.ConstraintStringAsNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Debito {
    
    @Schema(title= "Descripción de referencia de débito")
    @NotNull(message="{referencia.isNull}")
    @Size(min = 1 , max = 12 , message = "{referencia.size}")
    @Pattern(regexp = "^[0-9]{1,12}$" , message = "{referencia.isNumeric}")
    public String referencia;

    @Schema(title= "Monto a retirar con dos decimales sin comillas")
    @ConstraintStringAsNumber(maxSize = 12, message = "{monto.isNumeric}")
    public String monto;

    @Schema(title= "Token de seguridad para retiro")
    @NotNull(message="{pinBlock.isNull}")
    @Size(min = 16 , max = 16 , message = "{pinBlock.size}")
    @Pattern(regexp = "^[a-zA-Z0-9]{16}$" , message = "{pinBlock.isAlphaNumeric}")
    public String pinBlock;


}