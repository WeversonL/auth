package br.com.auth.exception;

import br.com.auth.exception.advice.DefaultControllerAdvice;
import br.com.auth.exception.model.ApiErrorResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultControllerAdviceTest {

    @Mock
    private BindException bindException;

    @Mock
    private ConstraintViolationException constraintViolationException;

    @InjectMocks
    private DefaultControllerAdvice defaultControllerAdvice;

    @Test
    public void testHandleUnauthorizedException() {
        ResponseEntity<ApiErrorResponse> responseEntity = defaultControllerAdvice.handleUnauthorizedException();
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void testHandleAccessDeniedException() {
        ResponseEntity<ApiErrorResponse> responseEntity = defaultControllerAdvice.handleAccessDeniedException();
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testHandleExpiredUserException() {
        ResponseEntity<ApiErrorResponse> responseEntity = defaultControllerAdvice.handleExpiredUserException();
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testHandleTokenDecodeException() {
        ResponseEntity<ApiErrorResponse> responseEntity = defaultControllerAdvice.handleTokenDecodeException();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testHandleTechnicalException() {
        ResponseEntity<ApiErrorResponse> responseEntity = defaultControllerAdvice.handleTechnicalException();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testOnBindException() {

        FieldError fieldError1 = new FieldError("objectName", "fieldName1", "Error message 1");
        FieldError fieldError2 = new FieldError("objectName", "fieldName2", "Error message 2");

        List<FieldError> errors = new ArrayList<>();
        errors.add(fieldError1);
        errors.add(fieldError2);

        when(bindException.getFieldErrors()).thenReturn(errors);

        ApiErrorResponse apiErrorResponse = defaultControllerAdvice.onBindException(bindException);

        assertEquals(Integer.valueOf(HttpStatus.BAD_REQUEST.value()), apiErrorResponse.getCode());
        assertEquals("Bad Request", apiErrorResponse.getMessage());
        assertEquals("Invalid request parameters", apiErrorResponse.getDescription());
        assertEquals(2, apiErrorResponse.getErrors().size());

        verify(bindException).getFieldErrors();
    }

}