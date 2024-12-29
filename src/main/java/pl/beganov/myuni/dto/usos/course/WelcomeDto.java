package pl.beganov.myuni.dto.usos.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public record WelcomeDto(
        @JsonProperty("course_editions") Map<String, List<CourseEditionUsosDto>> courseEditions
) {
}
