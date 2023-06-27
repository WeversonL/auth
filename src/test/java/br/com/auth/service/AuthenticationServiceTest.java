package br.com.auth.service;

import br.com.auth.domain.entity.UserEntity;
import br.com.auth.exception.impl.ExpiredUserException;
import br.com.auth.exception.impl.UnauthorizedException;
import br.com.auth.repository.UserRepository;
import br.com.auth.service.impl.AuthenticationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    private AuthenticationServiceImpl authenticationService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        authenticationService = new AuthenticationServiceImpl(userRepository);
    }

    @Test
    public void loadUserByUsername_ValidUsername_UserEntityReturned() {

        String username = "test_user";
        UserEntity expectedUser = new UserEntity();
        expectedUser.setUserName(username);
        expectedUser.setStatus(true);
        when(userRepository.findByUserName(username)).thenReturn(expectedUser);

        UserEntity actualUser = (UserEntity) authenticationService.loadUserByUsername(username);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void loadUserByUsername_InvalidUsername_UnauthorizedExceptionThrown() {

        String username = "invalid_user";
        when(userRepository.findByUserName(username)).thenReturn(null);

        assertThrows(UnauthorizedException.class, () -> authenticationService.loadUserByUsername(username));

        verify(userRepository).findByUserName(username);
    }

    @Test
    public void loadUserByUsername_ExpiredUser_ExpiredUserExceptionThrown() {

        String username = "expired_user";
        UserEntity expiredUser = new UserEntity();
        expiredUser.setUserName(username);
        expiredUser.setStatus(false);
        when(userRepository.findByUserName(username)).thenReturn(expiredUser);

        assertThrows(ExpiredUserException.class, () -> authenticationService.loadUserByUsername(username));

        verify(userRepository).findByUserName(username);
    }

}
