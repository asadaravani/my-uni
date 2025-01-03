package pl.beganov.myuni.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.beganov.myuni.service.usos.UsosUpdateService;

@RestController
@RequestMapping("/api/schedule")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseController {

    UsosUpdateService usosUpdateService;

    @PostMapping("/update")
    public ResponseEntity<?> updateSchedule(@RequestParam Long userId){
        return ResponseEntity.ok(usosUpdateService.updateCoursesByUserId(userId));
    }
}
