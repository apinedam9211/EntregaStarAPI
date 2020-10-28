package co.com.bcs.apibcs.exception;

import lombok.Getter;

@Getter
public class BackendException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final String codigoError;
  private final String descripcionUsuario;

  public BackendException(String message, String codigoError, String descripcionUsuario) {
    super(message);
    this.codigoError = codigoError;
    this.descripcionUsuario = descripcionUsuario;

  }

  public BackendException(){
    super("En este momento no podemos atenderlo");
    this.codigoError = "00001";
    this.descripcionUsuario =  "En este momento no podemos atenderlo";
  }

}