package br.com.auth.exception.impl;

import br.com.auth.enums.ResponseError;

import java.io.Serializable;

public class TechnicalException extends ApiException implements Serializable {

    private static final long serialVersionUID = -499955298425499177L;

    public TechnicalException() {
        super(ResponseError.TECHNICAL_EXCEPTION);
    }

    public TechnicalException(String message) {
        super(ResponseError.TECHNICAL_EXCEPTION, message);
    }

}
