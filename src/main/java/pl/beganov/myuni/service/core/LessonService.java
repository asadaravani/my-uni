package pl.beganov.myuni.service.core;

import pl.beganov.myuni.dto.out.LessonDto;
import pl.beganov.myuni.entity.AppUser;
import java.time.LocalDate;
import java.util.List;

public interface LessonService {

    void save(String responseBody, AppUser user);

    List<LessonDto> getLessonsByAppUser(Long id, LocalDate startDate, LocalDate endDate);
}
