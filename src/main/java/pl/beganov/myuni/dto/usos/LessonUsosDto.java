package pl.beganov.myuni.dto.usos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record LessonUsosDto(
        @JsonProperty("course_id") String courseId,
        @JsonProperty("course_name") Map<String, String> courseName,
        @JsonProperty("start_time") String startTime,
        @JsonProperty("end_time") String endTime,
        @JsonProperty("lecturer_ids") List<String> lecturerIds,
        @JsonProperty("room_number") String roomNumber,
        @JsonProperty("classtype_name") Map<String, String> classTypeName,
        @JsonProperty("group_number") Long groupNumber,
        @JsonProperty("url") String url,
        @JsonProperty("classgroup_profile_url") String classGroupProfileUrl
) {
}
