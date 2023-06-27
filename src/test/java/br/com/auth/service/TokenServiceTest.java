package br.com.auth.service;

import br.com.auth.domain.dto.TokenResponseDTO;
import br.com.auth.service.impl.TokenServiceImpl;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class TokenServiceTest {

    private TokenServiceImpl tokenService;

    @Mock
    private Algorithm algorithm;

    @Before
    public void setUp() {
        tokenService = new TokenServiceImpl("secret");
    }

    @Test
    public void generateToken_ValidUserDetails_TokenResponseDTOGenerated() {

        UserDetails userDetails = createUserDetails();
        Instant expirationTime = ZonedDateTime.now().plusMinutes(10).toInstant();
        TokenResponseDTO expectedTokenResponseDTO = new TokenResponseDTO("token", "Bearer", expirationTime);

        TokenResponseDTO actualTokenResponseDTO = tokenService.generateToken(userDetails);

        assertNotNull(actualTokenResponseDTO);
        assertEquals(expectedTokenResponseDTO.getTokenType(), actualTokenResponseDTO.getTokenType());
    }

    private UserDetails createUserDetails() {
        return User.withUsername("test_user")
                .password("test_password")
                .authorities("ROLE_USER")
                .build();
    }

}
