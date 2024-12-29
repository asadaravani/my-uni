package pl.beganov.myuni.service.core.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.beganov.myuni.dto.usos.UserResponse;
import pl.beganov.myuni.entity.AppUser;
import pl.beganov.myuni.entity.Course;
import pl.beganov.myuni.exception.UserNotFoundException;
import pl.beganov.myuni.mapper.AppUserMapper;
import pl.beganov.myuni.repository.AppUserRepository;
import pl.beganov.myuni.service.core.AppUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppUserServiceImpl implements AppUserService {
    AppUserRepository appUserRepository;
    ObjectMapper objectMapper;

    @Override
    public void save(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @SneakyThrows
    @Override
    public void saveAuthenticatedUser(String accessToken, String tokenSecret, String responseBody) {
        UserResponse response = objectMapper.readValue(responseBody, UserResponse.class);
        AppUser appUser = AppUserMapper.INSTANCE.responseToAppUser(response);
        appUser.setAccessToken(accessToken);
        appUser.setTokenSecret(tokenSecret);
        appUserRepository.save(appUser);
    }

    @Override
    public AppUser findById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: "+ id));
    }
    @Override
    public void saveCourses(List<Course> courses, AppUser appUser) {
        appUser.setCourses(courses);
        appUserRepository.save(appUser);
    }
}
