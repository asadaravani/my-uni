package pl.beganov.myuni.dto.usos.course;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserUsosDto(
        @JsonProperty("id") String id,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName
) {
}
