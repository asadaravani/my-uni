package pl.beganov.myuni.dto.usos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Set;

public record ActivityUsosDto(
        @JsonProperty("course_id") String courseId,
        @JsonProperty("start_time") String startTime,
        @JsonProperty("end_time") String endTime,
        @JsonProperty("lecturer_ids") Set<Long> lecturerIds,
        @JsonProperty("room_number") String room,
        @JsonProperty("classtype_name") Map<String, String> classtypeName,
        @JsonProperty("group_number") Long groupNumber
) {
}
