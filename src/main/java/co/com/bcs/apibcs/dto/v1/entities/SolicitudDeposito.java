package co.com.bcs.apibcs.dto.v1.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import co.com.bcs.apibcs.validators.ConstraintCelular;
import co.com.bcs.apibcs.validators.ConstraintDateFormatYYYYMMDD;
import co.com.bcs.apibcs.validators.ConstraintStringAsNumber;
import co.com.bcs.apibcs.validators.ConstraintTipoId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SolicitudDeposito {

	@Schema(title = "Tipo Identificación Cliente")
	@ConstraintTipoId(message = "{tipoId.noValid}")
	private String tipoDocumento;

	@Schema(title = "Número Identificación Cliente")
	@ConstraintStringAsNumber(maxSize = 18, message = "{numeroDocumento.isNull}")
	private String numeroDocumento;

	@Schema(title = "Primer Nombre Cliente")
	@NotNull(message = "{primerNombre.isNull}")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z ]{0,19}$", message = "{primerNombre.format}")
	private String primerNombre;

	@Schema(title = "Segundo Nombre Cliente")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z ]{0,29}$", message = "{segundoNombre.format}")
	private String segundoNombre;

	@Schema(title = "Numero Celular Cliente")
	@ConstraintCelular(message = "{celular.isNumeric}")
	private String celular;

	@Schema(title = "Primer Apellido Cliente")
	@NotNull(message = "{primerApellido.isNull}")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z ]{0,19}$", message = "{primerApellido.format}")
	private String primerApellido;

	@Schema(title = "Segundo Apellido Cliente")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z ]{0,29}$", message = "{segundoApellido.format}")
	private String segundoApellido;

	@Schema(title = "Correo Electrónico Cliente")
	@NotNull(message = "{email.isNull}")
	@NotEmpty(message = "{email.isEmpty}")
	@Email(message = "{email.format}")
	private String correoElectronico;

	@Schema(title = "Fecha Nacimiento Cliente")
	@ConstraintDateFormatYYYYMMDD(message = "{fechaNacimiento.format}")
	private String fechaNacimiento;

	@Schema(title = "Genero")
	@NotNull(message = "{genero.isNull}")
	@Pattern(message = "{genero.isNull}", regexp = "^M$|^F$")
	private String genero;

	@Schema(title = "Fecha Expedición del Documento")
	@ConstraintDateFormatYYYYMMDD(message = "{fechaExpedicionDocumento.format}")
	private String fechaExpedicionDocumento;

	@Schema(title = "Clave del Depósito Electrónico")
	@NotNull(message = "{clave.isNull}")
	@Size(min = 1, message = "{clave.size}")
	private String clave;

}