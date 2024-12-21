package pl.beganov.myuni.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import pl.beganov.myuni.enums.DeliveryMode;
import pl.beganov.myuni.enums.SessionType;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    Schedule schedule;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    LocalDateTime startTime;

    @Column(nullable = false)
    LocalDateTime endTime;

    @Column(nullable = false)
    SessionType type;

    @Column(nullable = false)
    String professor;

    @Column(nullable = false)
    DeliveryMode deliveryMode;

    @Column(nullable = false)
    String location;

    @Column(nullable = false)
    Integer groupNumber;

    @Column
    LocalDateTime lastUpdatedAt;
}
