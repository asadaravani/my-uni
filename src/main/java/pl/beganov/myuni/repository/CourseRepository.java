package pl.beganov.myuni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.beganov.myuni.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}
