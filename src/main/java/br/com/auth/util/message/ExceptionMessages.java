package br.com.auth.util.message;

public class ExceptionMessages {

    private ExceptionMessages() {
    }

    public static final String BAD_REQUEST_EXCEPTION = "Bad Request";
    public static final String UNAUTHORIZED_EXCEPTION = "Unauthorized";
    public static final String EXPIRED_USER_EXCEPTION = "Expired User";
    public static final String ACESS_DENIED_EXCEPTION = "Access Denied";
    public static final String TECHNICAL_EXCEPTION = "Technical Exception";
    public static final String TOKEN_DECODE_EXCEPTION = "Token Decode Error";
    public static final String BAD_REQUEST_DESCRIPTION = "Invalid request parameters";
    public static final String UNAUTHORIZED_DESCRIPTION = "Your account is not authorized. Get in touch with the administration";
    public static final String EXPIRED_USER_DESCRIPTION = "Your username is expired. Get in touch with the administration";
    public static final String ACESS_DENIED_DESCRIPTION = "Your access to the route has been denied. Check with the administration";
    public static final String TECHNICAL_DESCRIPTION = "There was a technical error on the server";
    public static final String TOKEN_DECODE_DESCRIPTION = "There was an error performing the decoding of the given token. Please generate a token on the correct endpoint and send it in the request header";

}
