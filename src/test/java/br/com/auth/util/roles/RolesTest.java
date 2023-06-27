package br.com.auth.util.roles;

import br.com.auth.util.message.ExceptionMessages;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RolesTest {

    @Test
    public void testAdminRole() {
        Assert.assertEquals("ADMIN", Roles.ADMIN_ROLE);
    }

    @Test
    public void testUserRole() {
        Assert.assertEquals("USER", Roles.USER_ROLE);
    }

}
