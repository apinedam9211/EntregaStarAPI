package co.com.bcs.apibcs.dto.v1.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import co.com.bcs.apibcs.validators.ConstraintCelular;
import co.com.bcs.apibcs.validators.ConstraintStringAsNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Otc {
    
    @Schema(title= "Valor retiró con dos decimales sin comillas")
	@NotNull(message = "{valorRetiro.isNull}")
	@Size(min = 1, message = "{valorRetiro.size}")
	@ConstraintStringAsNumber(maxSize = 12 , message = "{valorRetiro.noValid}")
	private String valorRetiro;

	@Schema(title= "Correo electronico de cliente")
	@NotNull(message = "{email.isNull}")
	@NotEmpty(message = "{email.isEmpty}")
	@Email(message = "{email.format}")
	private String correo;

    @Schema(title= "Número celular de cliente")
	@ConstraintCelular(message = "{celular.isNumeric}")
	private String celular;



}