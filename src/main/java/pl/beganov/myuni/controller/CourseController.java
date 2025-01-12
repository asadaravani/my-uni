package pl.beganov.myuni.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.beganov.myuni.service.core.ActivityService;
import pl.beganov.myuni.service.core.CourseService;

@RestController
@RequestMapping("/api/course")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseController {

    CourseService courseService;
    ActivityService activityService;

    @GetMapping
    public ResponseEntity<?> getAllCourses(@RequestParam Long userId){
        return ResponseEntity.ok(courseService);
    }

    @GetMapping("tt")
    public ResponseEntity<?> getActivities(@RequestParam Long userId){
        return ResponseEntity.ok(courseService);
    }
}
