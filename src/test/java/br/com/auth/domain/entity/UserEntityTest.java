package br.com.auth.domain.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserEntityTest {

    @Test
    public void getAuthorities_RoleSet_SingleAuthorityReturned() {

        String role = "ROLE_USER";
        UserEntity userEntity = new UserEntity();
        userEntity.setRole(role);

        Collection<? extends GrantedAuthority> authorities = userEntity.getAuthorities();

        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority(role)));
    }

    @Test
    public void getPassword_PasswordSet_PasswordReturned() {

        String password = "password";
        UserEntity userEntity = new UserEntity();
        userEntity.setPass(password);

        String actualPassword = userEntity.getPassword();

        assertEquals(password, actualPassword);
    }

    @Test
    public void getUsername_UsernameSet_UsernameReturned() {

        String username = "testuser";
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(username);

        String actualUsername = userEntity.getUsername();

        assertEquals(username, actualUsername);
    }

    @Test
    public void isAccountNonExpired_StatusSet_StatusReturned() {

        boolean status = true;
        UserEntity userEntity = new UserEntity();
        userEntity.setStatus(status);

        boolean actualStatus = userEntity.isAccountNonExpired();

        assertEquals(status, actualStatus);
    }

    @Test
    public void isAccountNonLocked_StatusSet_StatusReturned() {

        boolean status = true;
        UserEntity userEntity = new UserEntity();
        userEntity.setStatus(status);

        boolean actualStatus = userEntity.isAccountNonLocked();

        assertEquals(status, actualStatus);
    }

    @Test
    public void isCredentialsNonExpired_StatusSet_StatusReturned() {

        boolean status = true;
        UserEntity userEntity = new UserEntity();
        userEntity.setStatus(status);

        boolean actualStatus = userEntity.isCredentialsNonExpired();

        assertEquals(status, actualStatus);
    }

    @Test
    public void isEnabled_StatusSet_StatusReturned() {

        boolean status = true;
        UserEntity userEntity = new UserEntity();
        userEntity.setStatus(status);

        boolean actualStatus = userEntity.isEnabled();

        assertEquals(status, actualStatus);
    }

}
