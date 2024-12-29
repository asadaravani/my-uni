package pl.beganov.myuni.service.core.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.beganov.myuni.dto.usos.course.CourseEditionUsosDto;
import pl.beganov.myuni.dto.usos.course.WelcomeDto;
import pl.beganov.myuni.entity.AppUser;
import pl.beganov.myuni.entity.Course;
import pl.beganov.myuni.exception.ResourceNotFoundException;
import pl.beganov.myuni.mapper.CourseMapper;
import pl.beganov.myuni.repository.CourseRepository;
import pl.beganov.myuni.service.core.AppUserService;
import pl.beganov.myuni.service.core.EntityCollectionsService;
import pl.beganov.myuni.service.core.CourseService;
import pl.beganov.myuni.util.SemesterExtractor;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    ObjectMapper objectMapper;
    AppUserService appUserService;
    EntityCollectionsService entityCollectionsService;

    @SneakyThrows
    @Override
    public void save(String responseBody, AppUser user) {
        WelcomeDto dtos = objectMapper.readValue(responseBody, WelcomeDto.class);
        List<CourseEditionUsosDto> extractedDtos = SemesterExtractor.extractSemesters(dtos);
        List<Course> validEntities = extractedDtos.stream()
                .map(extractedDto -> CourseMapper.INSTANCE.dtoToEntity(extractedDto))
                .toList();
        courseRepository.saveAll(validEntities);
        appUserService.saveCourses(validEntities, user);
    }

    public Course findById(String id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }


}
