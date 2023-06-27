package br.com.auth.controller;

import br.com.auth.domain.dto.TokenResponseDTO;
import br.com.auth.domain.dto.UserDTO;
import br.com.auth.domain.dto.UserPermissionDTO;
import br.com.auth.service.TokenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Before
    public void setUp() {
        authController = new AuthController(authenticationManager, tokenService);
    }

    @Test
    public void testAuth() {
        UserDTO user = new UserDTO("username", "password");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);
        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO("token","token", LocalDateTime.now().toInstant(ZoneOffset.UTC));

        when(authenticationManager.authenticate(authenticationToken)).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(tokenService.generateToken(userDetails)).thenReturn(tokenResponseDTO);

        ResponseEntity<TokenResponseDTO> responseEntity = authController.auth(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tokenResponseDTO, responseEntity.getBody());

        Mockito.verify(authenticationManager).authenticate(authenticationToken);
        verify(authentication).getPrincipal();
        verify(tokenService).generateToken(userDetails);
    }

    @Test
    public void testPrivateRole() {
        ResponseEntity<UserPermissionDTO> responseEntity = authController.privateRole("auth");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("DEFAULT", Objects.requireNonNull(responseEntity.getBody()).getPermission());
    }

    @Test
    public void testPrivateAdmin() {
        ResponseEntity<UserPermissionDTO> responseEntity = authController.privateAdmin("auth");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("ADMIN", Objects.requireNonNull(responseEntity.getBody()).getPermission());
    }

    @Test
    public void testPrivateUser() {
        ResponseEntity<UserPermissionDTO> responseEntity = authController.privateUser("auth");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("USER", Objects.requireNonNull(responseEntity.getBody()).getPermission());
    }

}
