package br.com.auth.exception.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldErros implements Serializable {

    private static final long serialVersionUID = -1414631155785306250L;

    @Schema(description = "Error description", example = "Ocurred error in XXX")
    private String message;

    public FieldErros(String message) {
        this.message = message;
    }

    @JsonIgnore
    private transient Object[] parameters;

}