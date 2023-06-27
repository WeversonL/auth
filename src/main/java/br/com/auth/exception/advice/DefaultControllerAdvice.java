package br.com.auth.exception.advice;

import br.com.auth.exception.impl.ExpiredUserException;
import br.com.auth.exception.impl.TechnicalException;
import br.com.auth.exception.impl.TokenDecodeException;
import br.com.auth.exception.impl.UnauthorizedException;
import br.com.auth.exception.model.ApiErrorResponse;
import br.com.auth.exception.model.FieldErros;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static br.com.auth.enums.ResponseError.*;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException() {
        return ResponseEntity
                .status(ACESS_DENIED_EXCEPTION.getStatusCode())
                .body(ApiErrorResponse.builder()
                        .code(ACESS_DENIED_EXCEPTION.getStatusCode())
                        .description(ACESS_DENIED_EXCEPTION.getDescription())
                        .message(ACESS_DENIED_EXCEPTION.getMessage())
                        .build());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TokenDecodeException.class)
    public ResponseEntity<ApiErrorResponse> handleTokenDecodeException() {
        return ResponseEntity
                .status(TOKEN_DECODE_EXCEPTION.getStatusCode())
                .body(ApiErrorResponse.builder()
                        .code(TOKEN_DECODE_EXCEPTION.getStatusCode())
                        .description(TOKEN_DECODE_EXCEPTION.getDescription())
                        .message(TOKEN_DECODE_EXCEPTION.getMessage())
                        .build());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ExpiredUserException.class)
    public ResponseEntity<ApiErrorResponse> handleExpiredUserException() {
        return ResponseEntity
                .status(EXPIRED_USER_EXCEPTION.getStatusCode())
                .body(ApiErrorResponse.builder()
                        .code(EXPIRED_USER_EXCEPTION.getStatusCode())
                        .description(EXPIRED_USER_EXCEPTION.getDescription())
                        .message(EXPIRED_USER_EXCEPTION.getMessage())
                        .build());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class, UsernameNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> handleUnauthorizedException() {
        return ResponseEntity
                .status(UNAUTHORIZED_EXCEPTION.getStatusCode())
                .body(ApiErrorResponse.builder()
                        .code(UNAUTHORIZED_EXCEPTION.getStatusCode())
                        .description(UNAUTHORIZED_EXCEPTION.getDescription())
                        .message(UNAUTHORIZED_EXCEPTION.getMessage())
                        .build());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({TechnicalException.class, JsonProcessingException.class})
    public ResponseEntity<ApiErrorResponse> handleTechnicalException() {
        return ResponseEntity
                .status(TECHNICAL_EXCEPTION.getStatusCode())
                .body(ApiErrorResponse.builder()
                        .code(TECHNICAL_EXCEPTION.getStatusCode())
                        .description(TECHNICAL_EXCEPTION.getDescription())
                        .message(TECHNICAL_EXCEPTION.getMessage())
                        .build());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ApiErrorResponse onBindException(BindException bindException) {

        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .code(BAD_REQUEST_EXCEPTION.getStatusCode())
                .message(BAD_REQUEST_EXCEPTION.getMessage())
                .description(BAD_REQUEST_EXCEPTION.getDescription())
                .build();

        bindException.getAllErrors().forEach(error -> {
            String message = error.getDefaultMessage();
            String field = error instanceof FieldError ? ((FieldError) error).getField() : "";
            String formattedMessage = String.format(message, field);
            apiErrorResponse.getErrors().add(new FieldErros(formattedMessage));
        });

        return apiErrorResponse;

    }

}
