package br.com.auth.util.message;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionMessagesTest {

    @Test
    public void testBadRequestMessages() {
        Assert.assertEquals("Bad Request", ExceptionMessages.BAD_REQUEST_EXCEPTION);
        Assert.assertEquals("Invalid request parameters", ExceptionMessages.BAD_REQUEST_DESCRIPTION);
    }

    @Test
    public void testUnauthorizedMessages() {
        Assert.assertEquals("Unauthorized", ExceptionMessages.UNAUTHORIZED_EXCEPTION);
        Assert.assertEquals("Your account is not authorized. Get in touch with the administration", ExceptionMessages.UNAUTHORIZED_DESCRIPTION);
    }

    @Test
    public void testExpiredUserMessages() {
        Assert.assertEquals("Expired User", ExceptionMessages.EXPIRED_USER_EXCEPTION);
        Assert.assertEquals("Your username is expired. Get in touch with the administration", ExceptionMessages.EXPIRED_USER_DESCRIPTION);
    }

    @Test
    public void testAccessDeniedMessages() {
        Assert.assertEquals("Access Denied", ExceptionMessages.ACESS_DENIED_EXCEPTION);
        Assert.assertEquals("Your access to the route has been denied. Check with the administration", ExceptionMessages.ACESS_DENIED_DESCRIPTION);
    }

    @Test
    public void testTechnicalExceptionMessages() {
        Assert.assertEquals("Technical Exception", ExceptionMessages.TECHNICAL_EXCEPTION);
        Assert.assertEquals("There was a technical error on the server", ExceptionMessages.TECHNICAL_DESCRIPTION);
    }

    @Test
    public void testTokenDecodeErrorMessages() {
        Assert.assertEquals("Token Decode Error", ExceptionMessages.TOKEN_DECODE_EXCEPTION);
        Assert.assertEquals("There was an error performing the decoding of the given token. Please generate a token on the correct endpoint and send it in the request header", ExceptionMessages.TOKEN_DECODE_DESCRIPTION);
    }

}
