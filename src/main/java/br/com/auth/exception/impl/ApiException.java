package br.com.auth.exception.impl;

import br.com.auth.enums.ResponseError;
import br.com.auth.exception.model.ApiErrorResponse;

import java.io.Serializable;

public class ApiException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -5788423014332747578L;

    public ApiException(ResponseError responseError) {
        ApiErrorResponse.builder()
                .code(responseError.getStatusCode())
                .description(responseError.getDescription())
                .message(responseError.getMessage())
                .build();
    }

    public ApiException(ResponseError responseError, String message) {
        ApiErrorResponse.builder()
                .code(responseError.getStatusCode())
                .description(responseError.getDescription())
                .message(message)
                .build();
    }

}
