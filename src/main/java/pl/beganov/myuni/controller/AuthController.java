package pl.beganov.myuni.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.beganov.myuni.service.core.AuthService;
import pl.beganov.myuni.service.usos.UsosScheduleService;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;
    UsosScheduleService usosScheduleService;

    @PostMapping("/login")
    public String login() {
        return authService.authenticate();
    }

    @RequestMapping(value = "/callback", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> callback(
            @RequestParam("oauth_token") String oauthToken,
            @RequestParam("oauth_verifier") String oauthVerifier) {
        return ResponseEntity.ok(authService.handleCallback(oauthToken, oauthVerifier));
    }
    @GetMapping("/schedule")
    public ResponseEntity<?> getSchedule(@RequestParam Long userId) {
        return ResponseEntity.ok(usosScheduleService.getScheduleByUserId(userId));
    }
}
