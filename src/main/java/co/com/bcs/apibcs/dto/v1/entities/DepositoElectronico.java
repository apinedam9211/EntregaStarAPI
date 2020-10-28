package co.com.bcs.apibcs.dto.v1.entities;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositoElectronico {

    @Schema(title = "Id del depósito electrónico")
    private String id;

    public static DepositoElectronico fromData(String tipoDocumento, String numeroDocumento) {

        return DepositoElectronico.builder().id(tipoDocumento + "-" +numeroDocumento).build();

    }

}