package pl.beganov.myuni.dto.usos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
        @JsonProperty("id") String id,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName
) {
}
