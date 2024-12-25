package pl.beganov.myuni.mapper;

import org.junit.jupiter.api.Test;
import pl.beganov.myuni.dto.usos.UserResponse;
import pl.beganov.myuni.entity.AppUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppUserMapperTest {
    @Test
    public void testResponseToAppUser() {
        UserResponse userResponse = new UserResponse("123456", "Asadbek", "Beganov");
        AppUser appUser = AppUserMapper.INSTANCE.responseToAppUser(userResponse);
        assertEquals(userResponse.id(), appUser.getId().toString());
        assertEquals(userResponse.firstName(), appUser.getFirstName());
        assertEquals(userResponse.lastName(), appUser.getLastName());
    }
}
