package br.com.auth.filter;

import br.com.auth.domain.entity.UserEntity;
import br.com.auth.exception.impl.UnauthorizedException;
import br.com.auth.exception.model.ApiErrorResponse;
import br.com.auth.repository.UserRepository;
import br.com.auth.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static br.com.auth.enums.ResponseError.UNAUTHORIZED_EXCEPTION;

@Component
@RequiredArgsConstructor
public class FilterToken extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token;
        String authorization = request.getHeader("Authorization");

        if (Objects.nonNull(authorization)) {

            try {

                token = authorization.replace("Bearer ", "");
                String subject = tokenService.getSubject(token);
                UserEntity user = userRepository.findByUserName(subject);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (UnauthorizedException e) {
                buildResponse(response);
                return;
            }

        }

        filterChain.doFilter(request, response);

    }

    private static void buildResponse(HttpServletResponse response) throws IOException {
        ApiErrorResponse apiErrorResponse = buildResponseError();
        response.setStatus(apiErrorResponse.getCode());
        response.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().print(mapper.writeValueAsString(apiErrorResponse));
        response.getWriter().flush();
    }

    private static ApiErrorResponse buildResponseError() {
        return ApiErrorResponse.builder()
                .code(UNAUTHORIZED_EXCEPTION.getStatusCode())
                .description(UNAUTHORIZED_EXCEPTION.getMessage())
                .message(UNAUTHORIZED_EXCEPTION.getMessage())
                .build();
    }

}
