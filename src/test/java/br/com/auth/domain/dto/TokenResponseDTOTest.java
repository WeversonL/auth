package br.com.auth.domain.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TokenResponseDTOTest {

    @Test
    public void createAndGetToken_ValidToken_TokenSetAndReturned() {

        String token = "valid_token";

        TokenResponseDTO tokenResponseDTO = TokenResponseDTO.builder()
                .token(token)
                .build();
        String actualToken = tokenResponseDTO.getToken();

        assertEquals(token, actualToken);
    }

    @Test
    public void createAndGetTokenType_ValidTokenType_TokenTypeSetAndReturned() {

        String tokenType = "Bearer";

        TokenResponseDTO tokenResponseDTO = TokenResponseDTO.builder()
                .tokenType(tokenType)
                .build();
        String actualTokenType = tokenResponseDTO.getTokenType();

        assertEquals(tokenType, actualTokenType);
    }

    @Test
    public void createAndGetExpiresIn_ValidExpiresIn_ExpiresInSetAndReturned() {

        Instant expiresIn = Instant.now();

        TokenResponseDTO tokenResponseDTO = TokenResponseDTO.builder()
                .expiresIn(expiresIn)
                .build();
        Instant actualExpiresIn = tokenResponseDTO.getExpiresIn();

        assertEquals(expiresIn, actualExpiresIn);
    }

}
