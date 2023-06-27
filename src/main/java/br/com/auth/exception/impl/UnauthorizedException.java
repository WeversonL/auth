package br.com.auth.exception.impl;

import br.com.auth.enums.ResponseError;

import java.io.Serializable;

public class UnauthorizedException extends ApiException implements Serializable {

    private static final long serialVersionUID = 9089240502310264871L;

    public UnauthorizedException() {
        super(ResponseError.UNAUTHORIZED_EXCEPTION);
    }

}
