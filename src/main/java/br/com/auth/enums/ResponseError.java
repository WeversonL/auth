package br.com.auth.enums;

import br.com.auth.util.message.ExceptionMessages;
import lombok.Getter;

@Getter
public enum ResponseError {

    BAD_REQUEST_EXCEPTION(400, ExceptionMessages.BAD_REQUEST_EXCEPTION, ExceptionMessages.BAD_REQUEST_DESCRIPTION),
    UNAUTHORIZED_EXCEPTION(401, ExceptionMessages.UNAUTHORIZED_EXCEPTION, ExceptionMessages.UNAUTHORIZED_DESCRIPTION),
    EXPIRED_USER_EXCEPTION(403, ExceptionMessages.EXPIRED_USER_EXCEPTION, ExceptionMessages.EXPIRED_USER_DESCRIPTION),
    ACESS_DENIED_EXCEPTION(403, ExceptionMessages.ACESS_DENIED_EXCEPTION, ExceptionMessages.ACESS_DENIED_DESCRIPTION),
    TECHNICAL_EXCEPTION(500, ExceptionMessages.TECHNICAL_EXCEPTION, ExceptionMessages.TECHNICAL_DESCRIPTION),
    TOKEN_DECODE_EXCEPTION(500, ExceptionMessages.TOKEN_DECODE_EXCEPTION, ExceptionMessages.TOKEN_DECODE_DESCRIPTION);

    private final int statusCode;
    private final String message;
    private final String description;

    ResponseError(int statusCode, String message, String description) {
        this.statusCode = statusCode;
        this.message = message;
        this.description = description;
    }

}
