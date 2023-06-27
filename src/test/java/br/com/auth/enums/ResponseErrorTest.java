package br.com.auth.enums;

import br.com.auth.util.message.ExceptionMessages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ResponseErrorTest {

    @Test
    public void testTechnicalException() {
        ResponseError error = ResponseError.TECHNICAL_EXCEPTION;
        assertEquals(500, error.getStatusCode());
        assertEquals(ExceptionMessages.TECHNICAL_EXCEPTION, error.getMessage());
        assertEquals(ExceptionMessages.TECHNICAL_DESCRIPTION, error.getDescription());
    }

    @Test
    public void testBadRequestException() {
        ResponseError error = ResponseError.BAD_REQUEST_EXCEPTION;
        assertEquals(400, error.getStatusCode());
        assertEquals(ExceptionMessages.BAD_REQUEST_EXCEPTION, error.getMessage());
        assertEquals(ExceptionMessages.BAD_REQUEST_DESCRIPTION, error.getDescription());
    }

    @Test
    public void testUnauthorizedException() {
        ResponseError error = ResponseError.UNAUTHORIZED_EXCEPTION;
        assertEquals(401, error.getStatusCode());
        assertEquals(ExceptionMessages.UNAUTHORIZED_EXCEPTION, error.getMessage());
        assertEquals(ExceptionMessages.UNAUTHORIZED_DESCRIPTION, error.getDescription());
    }

    @Test
    public void testExpiredUserException() {
        ResponseError error = ResponseError.EXPIRED_USER_EXCEPTION;
        assertEquals(403, error.getStatusCode());
        assertEquals(ExceptionMessages.EXPIRED_USER_EXCEPTION, error.getMessage());
        assertEquals(ExceptionMessages.EXPIRED_USER_DESCRIPTION, error.getDescription());
    }

    @Test
    public void testAcessDeniedException() {
        ResponseError error = ResponseError.ACESS_DENIED_EXCEPTION;
        assertEquals(403, error.getStatusCode());
        assertEquals(ExceptionMessages.ACESS_DENIED_EXCEPTION, error.getMessage());
        assertEquals(ExceptionMessages.ACESS_DENIED_DESCRIPTION, error.getDescription());
    }

    @Test
    public void testTokenDecodeException() {
        ResponseError error = ResponseError.TOKEN_DECODE_EXCEPTION;
        assertEquals(500, error.getStatusCode());
        assertEquals(ExceptionMessages.TOKEN_DECODE_EXCEPTION, error.getMessage());
        assertEquals(ExceptionMessages.TOKEN_DECODE_DESCRIPTION, error.getDescription());
    }

}
