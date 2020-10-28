package co.com.bcs.apibcs.controller.ping;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import co.com.bcs.apibcs.dto.v1.entities.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(description = "Transaccion Ping para validar servicio activo", name =  "Ping" )
public class PingController {

	
	@Operation(summary = "Operacion Ping")
	@PostMapping(path = "/ping", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> ping(@Valid @RequestBody Message message) {

		final String nameResponse = "Hello " + Optional.ofNullable(message.getMessage()).orElse("user");
		return new ResponseEntity<>(nameResponse, HttpStatus.OK);
	
	}

}
