package br.com.auth.repository;

import br.com.auth.domain.entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void findByUserName_ValidUserName_UserEntityReturned() {

        String userName = "test_user";
        UserEntity expectedUser = new UserEntity();
        expectedUser.setUserName(userName);
        when(userRepository.findByUserName(userName)).thenReturn(expectedUser);

        UserEntity actualUser = userRepository.findByUserName(userName);

        assertEquals(expectedUser, actualUser);
    }

}
