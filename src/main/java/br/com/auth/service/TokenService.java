package br.com.auth.service;

import br.com.auth.domain.dto.TokenResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    TokenResponseDTO generateToken(UserDetails user);

    String getSubject(String token);
}
