package co.com.bcs.apibcs.dto.v1.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MedioManejo {
    
     @Schema(title = "Correo Electrónico Cliente")
	@NotNull(message = "{email.isNull}")
	@NotEmpty(message = "{email.isEmpty}")
	@Email(message = "{email.format}")
     private String correo;

     @Schema(title = "Dirección envio medio de manejo")
	@Pattern(regexp = "^^(\\w)(\\w|-| ){0,49}$", message = "{direccion.format}")
     private String direccion;
     
     @Schema(title = "Ciudad envio medio de manejo")
	@Pattern(regexp = "^(\\w)(\\w|-| ){0,49}$", message = "{ciudad.format}")
     private String ciudad;

}