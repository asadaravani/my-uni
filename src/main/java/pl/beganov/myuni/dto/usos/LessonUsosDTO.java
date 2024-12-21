package pl.beganov.myuni.dto.usos;

import java.util.Map;

public record LessonUsosDTO(
        String startTime,
        String endTime,
        Map<String, String> names
) {
}
