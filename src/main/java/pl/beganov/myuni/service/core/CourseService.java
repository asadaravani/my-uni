package pl.beganov.myuni.service.core;

import pl.beganov.myuni.entity.AppUser;
import pl.beganov.myuni.entity.Course;

public interface CourseService {

    void save(String responseBody, AppUser user);

    Course findById(String id);
}
