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
@RequestMapping("/api/update")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsosUpdateController {

    UsosUpdateService usosUpdateService;

    @PostMapping("/activities")
    public ResponseEntity<?> updateActivities(@RequestParam Long userId) {
        return ResponseEntity.ok(usosUpdateService.updateActivitiesByUserId(userId));
    }

    @PostMapping("/courses")
    public ResponseEntity<?> updateCourses(@RequestParam Long userId) {
        return ResponseEntity.ok(usosUpdateService.updateCoursesByUserId(userId));
    }
}
