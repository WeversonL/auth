package br.com.auth.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiErrorResponse implements Serializable {


    private static final long serialVersionUID = 5631580276971308252L;

    @JsonProperty("code")
    @Schema(description = "Exception status code", example = "int")
    private Integer code;

    @JsonProperty("message")
    @Schema(description = "Exception message", example = "string")
    private String message;

    @JsonProperty("description")
    @Schema(description = "Exception description", example = "string")
    private String description;

    @JsonProperty("errors")
    @Schema(description = "List of errors, if they need to be listed", example = "[{ \"error-x\": \"xxx\" }]")
    private List<FieldErros> errors;

    public List<FieldErros> getErrors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }

}
