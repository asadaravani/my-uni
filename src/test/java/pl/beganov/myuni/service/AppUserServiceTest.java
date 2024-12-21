package pl.beganov.myuni.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import pl.beganov.myuni.dto.UserResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppUserServiceTest {

    private String responseBody = "{\"id\": \"123456\", \"first_name\": \"Asadbek\", \"last_name\": \"Beganov\"}";

    @Test
    public void testObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        UserResponse userResponse;
        try {
            userResponse = objectMapper.readValue(responseBody, UserResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        assertEquals("123456", userResponse.id());
        assertEquals("Asadbek", userResponse.firstName());
        assertEquals("Beganov", userResponse.lastName());
    }
}
