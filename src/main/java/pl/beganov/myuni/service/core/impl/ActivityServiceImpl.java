package pl.beganov.myuni.service.core.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.beganov.myuni.dto.usos.ActivityUsosDto;
import pl.beganov.myuni.entity.Activity;
import pl.beganov.myuni.mapper.ActivityMapper;
import pl.beganov.myuni.repository.ActivityRepository;
import pl.beganov.myuni.service.core.ActivityService;
import pl.beganov.myuni.service.core.CourseService;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ActivityServiceImpl implements ActivityService {

    ActivityRepository activityRepository;
    CourseService courseService;
    ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void save(String responseBody) {
        List<ActivityUsosDto> dtos = objectMapper.readValue(responseBody, new TypeReference<>() {});
        List<Activity> activities = dtos.stream()
                .map(dto -> ActivityMapper.INSTANCE.dtoToEntity(dto, courseService.findById(dto.courseId())))
                .toList();
        activityRepository.saveAll(activities);
    }
}
