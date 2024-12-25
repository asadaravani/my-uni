package pl.beganov.myuni.service.core.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.beganov.myuni.dto.out.LessonDto;
import pl.beganov.myuni.dto.usos.LessonUsosDto;
import pl.beganov.myuni.entity.AppUser;
import pl.beganov.myuni.entity.Lesson;
import pl.beganov.myuni.mapper.LessonMapper;
import pl.beganov.myuni.repository.LessonRepository;
import pl.beganov.myuni.service.core.AppUserService;
import pl.beganov.myuni.service.core.LessonService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LessonServiceImpl implements LessonService {

    LessonRepository lessonRepository;
    ObjectMapper objectMapper;
    AppUserService appUserService;

    @SneakyThrows
    @Override
    public void save(String responseBody, AppUser user) {
        if (responseBody == null || responseBody.isEmpty()) {return;}
        List<LessonUsosDto> dtos = objectMapper.readValue(responseBody, new TypeReference<List<LessonUsosDto>>() {});
        System.out.println(dtos);
        List<Lesson> lessons = dtos.stream().map(LessonMapper.INSTANCE::usosDtoToLesson).toList();
        lessons.forEach(lesson -> {
            if (lesson.getAppUsers() == null) {
                lesson.setAppUsers(new ArrayList<>());
            }
            lesson.getAppUsers().add(user);
        });
        lessonRepository.saveAll(lessons);
    }

    @Override
    public List<LessonDto> getLessonsByAppUser(Long id, LocalDate startDate, LocalDate endDate) {
        endDate = endDate == null ? startDate.plusDays(7) : endDate;
        AppUser user = appUserService.findById(id);
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atStartOfDay();
        List<AppUser> appUsers = new ArrayList<>();
        appUsers.add(user);
        List<Lesson> lessons = lessonRepository.findAllByAppUsersAndStartTimeBetween(appUsers, startDateTime, endDateTime);
        return lessons.stream().map(
                        LessonMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
