package pl.beganov.myuni.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.beganov.myuni.dto.usos.course.CourseEditionUsosDto;
import pl.beganov.myuni.entity.Course;

import java.util.HashSet;
import java.util.Set;

public class CourseMapper {

    private CourseMapper() {}

    public static CourseMapper INSTANCE = new CourseMapper();

    public Course dtoToEntity(CourseEditionUsosDto dto) {
        return Course.builder()
                .id(dto.courseId())
                .name(dto.courseName().get("en"))
                .termId(dto.termId())
                .url(dto.userGroups().get(0).courseProfileUrl())
                .lecturers(extractLecturers(dto))
                .build();
    }
    private Set<String> extractLecturers(CourseEditionUsosDto dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<String> lecturers = new HashSet<>();
        dto.userGroups().forEach(userGroup -> userGroup.lecturers()
                .forEach(lecturer -> {
                    try {
                        lecturers.add(objectMapper.writeValueAsString(lecturer));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }));
        return lecturers;
    }
}

