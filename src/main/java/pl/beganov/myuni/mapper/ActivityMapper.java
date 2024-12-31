package pl.beganov.myuni.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import pl.beganov.myuni.dto.usos.ActivityUsosDto;
import pl.beganov.myuni.dto.usos.course.UserUsosDto;
import pl.beganov.myuni.entity.Activity;
import pl.beganov.myuni.entity.Course;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mapper
public interface ActivityMapper {
    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

    @Mapping(source = "dto.startTime", target = "startTime", qualifiedByName = "extractDateFromString")
    @Mapping(source = "dto.endTime", target = "endTime", qualifiedByName = "extractDateFromString")
    @Mapping(source = "dto.classtypeName", target = "classType", qualifiedByName = "extractClassType")
    @Mapping(source = "course", target = "course")
    @Mapping(target = "lecturers", expression = "java(mapLecturers(dto.lecturerIds(), course))")
    @Mapping(target = "id", ignore = true)
    Activity dtoToEntity(ActivityUsosDto dto, Course course);

    @Named("extractDateFromString")
    default LocalDateTime extractDateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }
    @Named("extractClassType")
    default String extractClassType(Map<String, String> classTypeName){
        return classTypeName != null ? classTypeName.getOrDefault("en", "Unknown") : "Unknown";
    }

    @SneakyThrows
    default Set<String> mapLecturers(Set<Long> ids, Course course) {
        if (ids == null || ids.isEmpty() || course.getLecturers() == null || course.getLecturers().isEmpty()) {
            return Set.of();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Set<UserUsosDto> lecturers = new HashSet<>();
        for(String lecturerString : course.getLecturers()) {
            UserUsosDto lecturer = objectMapper.readValue(lecturerString, UserUsosDto.class);
            lecturers.add(lecturer);
        }
        Set<String> lecturerNames = new HashSet<>();
        lecturers.stream()
                .filter(lecturer -> ids.contains(Long.parseLong(lecturer.id())))
                .forEach(lecturer -> lecturerNames.add(lecturer.firstName() + " " + lecturer.lastName()));

        return lecturerNames.isEmpty() ? Set.of("Unknown Lecturer") : lecturerNames;
    }
}
