package pl.beganov.myuni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.beganov.myuni.entity.AppUser;
import pl.beganov.myuni.entity.Lesson;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllByAppUsersAndStartTimeBetween(List<AppUser> appUsers, LocalDateTime startTime, LocalDateTime startTime2);
}
