package pl.beganov.myuni.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = false, of = "id")
public class Course extends BaseEntity{

    @Id
    String id;

    String name;

    String termId;

    String url;

    @ElementCollection(fetch = FetchType.EAGER)
    Set<String> lecturers;
}
