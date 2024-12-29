package pl.beganov.myuni.service.core;

import pl.beganov.myuni.entity.AppUser;
import pl.beganov.myuni.entity.Course;

import java.util.List;

public interface AppUserService {

    void save(AppUser appUser);

    void saveAuthenticatedUser(String accessToken, String tokenSecret, String responseBody);

    AppUser findById(Long id);

    void saveCourses(List<Course> courses, AppUser appUser);
}
