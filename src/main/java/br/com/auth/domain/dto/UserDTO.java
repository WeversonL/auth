package br.com.auth.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import static br.com.auth.util.message.MessageValidation.FIELD_IS_REQUIRED;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 7295627542793365960L;

    @NotBlank(message = FIELD_IS_REQUIRED)
    @Schema(description = "User name", example = "simple-user")
    private String username;

    @NotBlank(message = FIELD_IS_REQUIRED)
    @Schema(description = "User password", example = "strong@pass")
    private String password;

}
