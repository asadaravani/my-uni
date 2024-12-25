package pl.beganov.myuni.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import pl.beganov.myuni.dto.out.LessonDto;
import pl.beganov.myuni.dto.usos.LessonUsosDto;
import pl.beganov.myuni.entity.Lesson;
import pl.beganov.myuni.enums.ClassType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Mapper
public interface LessonMapper {
    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    LessonDto toDto(Lesson lesson);

    @Mapping(source = "courseId", target = "id")
    @Mapping(source = "courseName", target = "name", qualifiedByName = "extractEnglishValue")
    @Mapping(source = "classTypeName", target = "classType", qualifiedByName = "extractClassType")
    @Mapping(source = "roomNumber", target = "room")
    @Mapping(source = "classGroupProfileUrl", target = "groupProfileUrl")
    @Mapping(source = "startTime", target = "startTime", qualifiedByName = "stringToLocalDateTime")
    @Mapping(source = "endTime", target = "endTime", qualifiedByName = "stringToLocalDateTime")
    Lesson usosDtoToLesson(LessonUsosDto lessonUsosDTO);

    @Named("extractClassType")
    static ClassType extractClassType(Map<String, String> map) {
        return ClassType.valueOf(map.get("en").toUpperCase());
    }

    @Named("extractEnglishValue")
    static String extractEnglishValue(Map<String, String> map) {
        return map.get("en");
    }

    @Named("stringToLocalDateTime")
    static LocalDateTime stringToLocalDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTimeString, formatter);
    }
}
