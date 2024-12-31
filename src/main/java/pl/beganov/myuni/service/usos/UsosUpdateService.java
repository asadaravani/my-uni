package pl.beganov.myuni.service.usos;

import lombok.SneakyThrows;

public interface UsosUpdateService {

    String updateCoursesByUserId(Long userId);

    @SneakyThrows
    String updateActivitiesByUserId(Long userId);
}
