package br.com.auth.service.impl;

import br.com.auth.domain.dto.TokenResponseDTO;
import br.com.auth.exception.impl.TechnicalException;
import br.com.auth.exception.impl.UnauthorizedException;
import br.com.auth.service.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    private static final String TOKEN_TYPE = "Bearer";
    private static final String ISSUER = "PERSONS";
    private static final Integer TOKEN_EXPIRATION_MINUTES = 10;

    private final String secret;
    public TokenServiceImpl(@Value("${security.secret}") String secret) {
        this.secret = secret;
    }

    @Override
    public TokenResponseDTO generateToken(UserDetails user) {

        Instant expirationTime = ZonedDateTime.now()
                .plusMinutes(TOKEN_EXPIRATION_MINUTES).toInstant();

        String token = JWT.create()
                .withIssuer(ISSUER)
                .withSubject(user.getUsername())
                .withExpiresAt(expirationTime)
                .sign(Algorithm.HMAC256(secret));

        log.info("Token generated for user: {}", user.getUsername());

        return TokenResponseDTO.builder()
                .token(token)
                .tokenType(TOKEN_TYPE)
                .expiresIn(expirationTime)
                .build();
    }

    public String getSubject(String token) {
        try {

            String subject = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(ISSUER)
                    .build().verify(token).getSubject();

            log.info("Subject extracted from token: {}", subject);

            return subject;

        } catch (JWTDecodeException e) {
            log.error("Invalid token provided: {}", e.getMessage());
            throw new TechnicalException("Invalid token provided");
        } catch (TokenExpiredException e) {
            log.error("Expired token provided");
            throw new UnauthorizedException();
        }

    }

}
