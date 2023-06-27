package br.com.auth.filter;

import br.com.auth.domain.entity.UserEntity;
import br.com.auth.exception.impl.UnauthorizedException;
import br.com.auth.exception.model.ApiErrorResponse;
import br.com.auth.repository.UserRepository;
import br.com.auth.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

import static br.com.auth.enums.ResponseError.UNAUTHORIZED_EXCEPTION;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FilterTokenTest {

    private FilterToken filterToken;

    @Mock
    private TokenService tokenService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        filterToken = new FilterToken(tokenService, userRepository);
    }

    @Test
    public void doFilterInternal_ValidAuthorization_TokenIsValid() throws ServletException, IOException {

        String token = "valid_token";
        String userName = "test_user";
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setPass("123");
        userEntity.setStatus(true);
        userEntity.setRole("ROLE_USER");
        userEntity.setId(1L);
        when(tokenService.getSubject(token)).thenReturn(userName);
        when(userRepository.findByUserName(userName)).thenReturn(userEntity);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer " + token);
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        filterToken.doFilterInternal(request, response, filterChain);

        verify(tokenService).getSubject(token);
        verify(userRepository).findByUserName(userName);
        verify(filterChain).doFilter(request, response);
        assertEquals(userEntity, SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Test
    public void doFilterInternal_InvalidAuthorization_UnauthorizedExceptionThrown() throws ServletException, IOException {

        String token = "invalid_token";
        when(tokenService.getSubject(token)).thenThrow(new UnauthorizedException());
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer " + token);
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        filterToken.doFilterInternal(request, response, filterChain);

        verify(tokenService).getSubject(token);
        verifyNoInteractions(userRepository);
        verify(filterChain, never()).doFilter(request, response);
        assertEquals(UNAUTHORIZED_EXCEPTION.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        ApiErrorResponse expectedErrorResponse = buildResponseError();
        assertEquals(expectedErrorResponse, parseResponseError(response));
    }

    private ApiErrorResponse parseResponseError(MockHttpServletResponse response) throws IOException {
        String responseBody = response.getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseBody, ApiErrorResponse.class);
    }

    private ApiErrorResponse buildResponseError() {
        return ApiErrorResponse.builder()
                .code(UNAUTHORIZED_EXCEPTION.getStatusCode())
                .description(UNAUTHORIZED_EXCEPTION.getMessage())
                .message(UNAUTHORIZED_EXCEPTION.getMessage())
                .build();
    }

}
