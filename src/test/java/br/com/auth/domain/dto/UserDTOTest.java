package br.com.auth.domain.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class UserDTOTest {

    @Test
    public void createAndGetUsername_ValidUsername_UsernameSetAndReturned() {

        String username = "john.doe";

        UserDTO userDTO = UserDTO.builder()
                .username(username)
                .build();
        String actualUsername = userDTO.getUsername();

        assertEquals(actualUsername, username);
    }

    @Test
    public void createAndGetUsername_DefaultConstructor_UsernameNotSet() {

        UserDTO userDTO = new UserDTO();
        String actualUsername = userDTO.getUsername();

        assertNull(actualUsername);
    }

    @Test
    public void createAndGetUsername_AllArgsConstructor_UsernameSetAndReturned() {

        String username = "jane.doe";

        UserDTO userDTO = new UserDTO(username, "password");
        String actualUsername = userDTO.getUsername();

        assertEquals(actualUsername, username);
    }

    @Test
    public void createAndGetPassword_ValidPassword_PasswordSetAndReturned() {

        String password = "123456";

        UserDTO userDTO = UserDTO.builder()
                .password(password)
                .build();
        String actualPassword = userDTO.getPassword();

        assertEquals(actualPassword, password);
    }

    @Test
    public void createAndGetPassword_DefaultConstructor_PasswordNotSet() {

        UserDTO userDTO = new UserDTO();
        String actualPassword = userDTO.getPassword();

        assertNull(actualPassword);
    }

    @Test
    public void createAndGetPassword_AllArgsConstructor_PasswordSetAndReturned() {

        String password = "abcdef";

        UserDTO userDTO = new UserDTO("johndoe", password);
        String actualPassword = userDTO.getPassword();

        assertEquals(actualPassword, password);
    }

}
