package co.com.bcs.apibcs.dto.v1.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CabeceraRespuestaErronea {

    @Schema(title= "Código de error, cuando status es ER")
    private String errorCode;
    @Schema(title= "Descripción de error, cuando status es ER")
    private String descriptionError;
    @Schema(title= "Estado de la transacción")
    private String status;

    public String getErrorCode() {

        if (errorCode == null) {
            errorCode = "";
        }
        return errorCode;

    }

    public String getDescriptionError() {

        if (descriptionError == null) {
            descriptionError = "";
        }
        return descriptionError;

    }

    public static CabeceraRespuestaErronea crearCabeceraErronea(String errorCode, String descripcionError) {
        return CabeceraRespuestaErronea.builder().status("ER").descriptionError(descripcionError).errorCode(errorCode)
                .build();
    }

}