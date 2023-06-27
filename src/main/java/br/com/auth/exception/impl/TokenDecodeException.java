package br.com.auth.exception.impl;

import br.com.auth.enums.ResponseError;

import java.io.Serializable;

public class TokenDecodeException extends ApiException implements Serializable {

    private static final long serialVersionUID = 835830539628688488L;

    public TokenDecodeException() {
        super(ResponseError.UNAUTHORIZED_EXCEPTION);
    }

}
