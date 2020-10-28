package co.com.bcs.apibcs.exception;

import lombok.Getter;

@Getter
public class Error500Exception extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String codigoError;
    private final String descripcionUsuario;

    public Error500Exception(String message, String codigoError, String descripcionUsuario) {
        super(message);
        this.codigoError = codigoError;
        this.descripcionUsuario = descripcionUsuario;

    }

}