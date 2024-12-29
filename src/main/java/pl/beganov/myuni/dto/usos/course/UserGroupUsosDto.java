package pl.beganov.myuni.dto.usos.course;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record UserGroupUsosDto(
        @JsonProperty("course_unit_id") String courseUnitId,
        @JsonProperty("group_number") Long groupNumber,
        @JsonProperty("class_type") Map<String, String> classType,
        @JsonProperty("class_type_id") String classTypeId,
        @JsonProperty("group_url") String groupUrl,
        @JsonProperty("course_id") String courseId,
        @JsonProperty("course_name") Map<String, String> courseName,
        @JsonProperty("course_homepage_url") Object courseHomepageUrl,
        @JsonProperty("course_profile_url") String courseProfileUrl,
        @JsonProperty("course_is_currently_conducted") Long courseIsCurrentlyConducted,
        @JsonProperty("course_fac_id") String courseFacId,
        @JsonProperty("course_lang_id") String courseLangId,
        @JsonProperty("term_id") String termId,
        @JsonProperty("lecturers") List<UserUsosDto> lecturers
) {
}
