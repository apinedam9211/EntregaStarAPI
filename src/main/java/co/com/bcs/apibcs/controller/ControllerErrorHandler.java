package co.com.bcs.apibcs.controller;

import java.util.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.bcs.apibcs.dto.v1.entities.CabeceraRespuestaErronea;
import co.com.bcs.apibcs.exception.BackendException;
import co.com.bcs.apibcs.exception.NotFoundException;

@ControllerAdvice
public class ControllerErrorHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Optional<String> descripcionError = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.reduce((x, y) -> x + "," + y);
		return new ResponseEntity<>(CabeceraRespuestaErronea.crearCabeceraErronea("VAL", descripcionError.orElse("En este momento no podemos atenderlo")), headers,
				status);
	}
	/* @ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Optional<String> descripcionError = ex.getConstraintViolations().stream().
		map(ConstraintViolation::getMessage).reduce((x, y) -> x + "," + y);
		return new ResponseEntity<>(CabeceraRespuestaErronea.crearCabeceraErronea("VAL", descripcionError.orElse("En este momento no podemos atenderlo")), headers,
				status);
	} */

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<>(CabeceraRespuestaErronea.crearCabeceraErronea("0002", "Method Not Allowed"),
				status);
	}

	// TODO. definir objeto de respuestas erroneas.

	@ExceptionHandler(BackendException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<CabeceraRespuestaErronea> backendException(BackendException ex, WebRequest request) {
		CabeceraRespuestaErronea respuesta = CabeceraRespuestaErronea.crearCabeceraErronea(ex.getCodigoError(),
				ex.getDescripcionUsuario());

		return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> NotFoundException(NotFoundException ex, WebRequest request) {
		CabeceraRespuestaErronea respuesta = CabeceraRespuestaErronea.crearCabeceraErronea("404", ex.getMessage());

		return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> lastException(Exception ex, WebRequest request) {
		CabeceraRespuestaErronea respuesta = CabeceraRespuestaErronea.crearCabeceraErronea("0001",
				"En este momento no podemos atenderlo");
		return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}