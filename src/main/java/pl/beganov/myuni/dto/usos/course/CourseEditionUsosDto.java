package pl.beganov.myuni.dto.usos.course;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record CourseEditionUsosDto(
        @JsonProperty("course_id") String courseId,
        @JsonProperty("course_name") Map<String, String> courseName,
        @JsonProperty("term_id") String termId,
        @JsonProperty("user_groups") List<UserGroupUsosDto> userGroups
) {
}
