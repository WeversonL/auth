package br.com.auth.exception.impl;

import br.com.auth.enums.ResponseError;

import java.io.Serializable;

public class ExpiredUserException extends ApiException implements Serializable {

    private static final long serialVersionUID = 5787803868050058553L;

    public ExpiredUserException() {
        super(ResponseError.EXPIRED_USER_EXCEPTION);
    }

}
