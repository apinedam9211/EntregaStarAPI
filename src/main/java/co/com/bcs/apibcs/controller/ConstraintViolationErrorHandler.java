package co.com.bcs.apibcs.controller;

import java.util.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.com.bcs.apibcs.dto.v1.entities.CabeceraRespuestaErronea;

@ControllerAdvice
public class ConstraintViolationErrorHandler{

	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		Optional<String> descripcionError = ex.getConstraintViolations().stream().
		map(ConstraintViolation::getMessage).reduce((x, y) -> x + "," + y);
		return new ResponseEntity<>(CabeceraRespuestaErronea.crearCabeceraErronea("VAL", descripcionError.orElse("En este momento no podemos atenderlo")),
				HttpStatus.BAD_REQUEST);
	} 
}