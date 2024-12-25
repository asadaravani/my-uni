package pl.beganov.myuni.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import pl.beganov.myuni.enums.ClassType;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson extends BaseEntity{

    @Id
    @Column
    String id;

    @ManyToMany
            @JoinTable(
                    name = "lesson_app_users",
                    joinColumns = @JoinColumn(name = "lesson_id"),
                    inverseJoinColumns = @JoinColumn(name = "app_user_id")
            )
    List<AppUser> appUsers;

    @Column
    String name;

    @Column
    LocalDateTime startTime;

    @Column
    LocalDateTime endTime;

    @Column
    ClassType classType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "lesson_lecturer_ids", joinColumns = @JoinColumn(name = "lesson_id"))
    @Column(name = "lecturer_id")
    List<Long> lecturerIds;

    @Column
    String room;

    @Column
    Long groupNumber;

    @Column
    String url;

    @Column
    String groupProfileUrl;
}
