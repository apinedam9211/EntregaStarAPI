package co.com.bcs.apibcs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.com.bcs.apibcs.dto.v1.entities.CabeceraRespuestaErronea;

class ControllErrorHandlerTest {

    @Test
    void sendErrorMethodNotAllowed() {

        ControllerErrorHandler errorHandler = new ControllerErrorHandler();
        ResponseEntity<Object> response = errorHandler.handleHttpRequestMethodNotSupported(null, null,
                HttpStatus.METHOD_NOT_ALLOWED, null);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode(), "http status erroneo");

        CabeceraRespuestaErronea body = (CabeceraRespuestaErronea) response.getBody();
        assertEquals("ER", body.getStatus(), "Codigo respuesta erroneo");

    }

}