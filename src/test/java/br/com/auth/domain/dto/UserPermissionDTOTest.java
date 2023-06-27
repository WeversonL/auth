package br.com.auth.domain.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class UserPermissionDTOTest {

    @Test
    public void createAndGetPermission_ValidPermission_PermissionSetAndReturned() {

        String permission = "ADMIN";

        UserPermissionDTO userPermissionDTO = UserPermissionDTO.builder()
                .permission(permission)
                .build();
        String actualPermission = userPermissionDTO.getPermission();

        assertEquals(permission, actualPermission);
    }

    @Test
    public void createAndGetPermission_AllArgsConstructor_PermissionSetAndReturned() {

        String permission = "USER";

        UserPermissionDTO userPermissionDTO = new UserPermissionDTO(permission);
        String actualPermission = userPermissionDTO.getPermission();

        assertEquals(permission, actualPermission);
    }

    @Test
    public void createAndGetPermission_DefaultConstructor_PermissionNotSet() {

        UserPermissionDTO userPermissionDTO = new UserPermissionDTO();
        String actualPermission = userPermissionDTO.getPermission();

        assertNull(actualPermission);
    }

}
