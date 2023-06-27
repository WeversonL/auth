package br.com.auth.controller;

import br.com.auth.domain.dto.TokenResponseDTO;
import br.com.auth.domain.dto.UserDTO;
import br.com.auth.domain.dto.UserPermissionDTO;
import br.com.auth.service.TokenService;
import br.com.auth.util.roles.Roles;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication resource")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private static final String TOKEN_EXAMPLE = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

    @PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Generate token", description = "This method generate JWT Token")
    public ResponseEntity<TokenResponseDTO> auth(
            @Valid
            @Parameter(in = ParameterIn.QUERY, description = "User parameters", required = true)
            @ModelAttribute UserDTO user) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        Authentication authenticate = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        UserDetails userAuth = (UserDetails) authenticate.getPrincipal();

        TokenResponseDTO token = tokenService.generateToken(userAuth);

        return ResponseEntity.ok(token);

    }

    @GetMapping(value = "/private-role", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Test PRIVATE role", description = "This method valid a role that needs authentication")
    public ResponseEntity<UserPermissionDTO> privateRole(
            @RequestHeader(value = "Authorization")
            @Parameter(example = TOKEN_EXAMPLE) String authorization) {
        return ResponseEntity.ok(new UserPermissionDTO("DEFAULT"));
    }

    @GetMapping(value = "/admin-role", produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({Roles.ADMIN_ROLE})
    @Operation(summary = "Test ADMIN role", description = "This method valid a role that needs ADMIN authentication")
    public ResponseEntity<UserPermissionDTO> privateAdmin(
            @RequestHeader(value = "Authorization")
            @Parameter(example = TOKEN_EXAMPLE) String authorization) {
        return ResponseEntity.ok(new UserPermissionDTO("ADMIN"));
    }

    @GetMapping(value = "/user-role", produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({Roles.ADMIN_ROLE, Roles.USER_ROLE})
    @Operation(summary = "Test USER role", description = "This method valid a role that needs USER authentication")
    public ResponseEntity<UserPermissionDTO> privateUser(
            @RequestHeader(value = "Authorization")
            @Parameter(example = TOKEN_EXAMPLE) String authorization) {
        return ResponseEntity.ok(new UserPermissionDTO("USER"));
    }

}
