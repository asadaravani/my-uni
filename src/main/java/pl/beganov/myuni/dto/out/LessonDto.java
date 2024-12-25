package pl.beganov.myuni.dto.out;

import pl.beganov.myuni.enums.ClassType;
import java.time.LocalDateTime;
import java.util.List;

public record LessonDto(
        String id,
        String name,
        LocalDateTime startTime,
        LocalDateTime endTime,
        ClassType classType,
        List<Long> lecturerIds,
        String room,
        Long groupNumber

) {
}
