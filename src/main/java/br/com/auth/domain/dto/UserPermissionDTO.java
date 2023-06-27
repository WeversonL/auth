package br.com.auth.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionDTO implements Serializable {

    private static final long serialVersionUID = 1256261841755136948L;

    @Schema(description = "User permission", example = "string")
    private String permission;

}
