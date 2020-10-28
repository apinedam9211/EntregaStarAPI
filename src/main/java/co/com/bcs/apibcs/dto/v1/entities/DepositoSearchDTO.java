package co.com.bcs.apibcs.dto.v1.entities;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import co.com.bcs.apibcs.validators.ConstraintCelular;
import co.com.bcs.apibcs.validators.ConstraintStringAsNumber;
import co.com.bcs.apibcs.validators.ConstraintTipoId;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Hidden
public class DepositoSearchDTO {

    @Schema(title= "Tipo de identificación de cliente con depósito electrónico")
	@ConstraintTipoId(message = "{tipoId.noValid}")
	private String tipoDocumento;

    @Schema(title= "Número de identificación de cliente con depósito electrónico")
	@ConstraintStringAsNumber(maxSize = 18, message = "{numeroDocumento.isNull}")
	private String numeroDocumento;

    @Schema(title= "Correo electrónico de cliente")
	@NotNull(message = "{email.isNull}")
	@NotEmpty(message = "{email.isEmpty}")
	@Email(message = "{email.format}")
	private String correo;

    @Schema(title= "Número celular de cliente")
	@ConstraintCelular(message = "{celular.isNumeric}")
	private String celular;

	
}