package co.com.bcs.apibcs.dto.v1.entities;

import co.com.bcs.apibcs.exception.NotFoundException;
import co.com.bcs.apibcs.validators.ConstraintStringAsNumber;
import co.com.bcs.apibcs.validators.ConstraintTipoId;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Hidden
public class DepositoID {

    @Schema(title = "Tipo de identificación de cliente con depósito electrónico")
    @ConstraintTipoId(message = "{tipoId.noValid}")
    private String tipoDocumento;

    @Schema(title = "Número de identificación de cliente con depósito electrónico")
    @ConstraintStringAsNumber(maxSize = 18, message = "{numeroDocumento.isNull}")
    private String numeroDocumento;

    public static DepositoID fromValue(String text) {

        if (text == null)
            throw new NotFoundException("Depósito Electrónico No Encontrado");
        String[] splitText = text.split("-");

        if (splitText.length < 2)
            throw new NotFoundException("Deposito Electronico No Encontrado");

        DepositoID newdeposito = new DepositoID();
        newdeposito.setTipoDocumento(splitText[0]);
        newdeposito.setNumeroDocumento(splitText[1]);

        return newdeposito;
    }

}