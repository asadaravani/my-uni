package pl.beganov.myuni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.beganov.myuni.entity.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
