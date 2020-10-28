package co.com.bcs.apibcs.dto.v1.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cabecera {


	@Schema(title = "Número Referencia")
	@Size(min = 1, message = "{numeroReferencia.size}")
	@NotNull(message = "{numeroReferencia.isNull}")
	private String numeroReferencia;

	@Schema(title = "País Origen Transacción")
	@Size(min = 1, max = 2, message = "{paisOrigen.size}")
	@NotNull(message = "{paisOrigen.isNull}")
	private String paisOrigen;

	@Schema(title = "IP Servidor")
	@Size(min = 1, max = 20, message = "{ipServidorCorresponsal.size}")
	@NotNull(message = "{ipServidorCorresponsal.isNull}")
	private String ipServidorCorresponsal;

	@Schema(title = "Dirección MAC Corresponsal")
	@Size(min = 1, max = 20, message = "{macServidorCorresponsal.size}")
	@NotNull(message = "{macServidorCorresponsal.isNull}")
	private String macServidorCorresponsal;

	@Schema(title = "IP Cliente")
	@Size(min = 1, max = 20, message = "{ipClienteCorresponsal.size}")
	@NotNull(message = "{ipClienteCorresponsal.isNull}")
	private String ipClienteCorresponsal;

	@Schema(title = "Dirección MAC Cliente")
	@Size(min = 1, max = 20, message = "{macClienteCorresponsal.size}")
	@NotNull(message = "{macClienteCorresponsal.isNull}")
	private String macClienteCorresponsal;

	@Schema(title = "Código Hash-Message")
	private String hashMessage;

}
