package pl.beganov.myuni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.beganov.myuni.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

}
