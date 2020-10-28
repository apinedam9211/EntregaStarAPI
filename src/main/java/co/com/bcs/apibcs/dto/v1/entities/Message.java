package co.com.bcs.apibcs.dto.v1.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {

    @Schema(title= "Mensaje Ping")
    @NotNull(message = "{message.isNull}")
    @NotEmpty(message = "{message.notEmpty}")
    private String message;

}