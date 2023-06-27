package br.com.auth.util.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MessageValidationTest {

    @Test
    public void testFieldRequiredMessage() {
        String message = MessageValidation.FIELD_IS_REQUIRED;
        assertEquals("%s is required", message);
    }

}
