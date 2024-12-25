package pl.beganov.myuni.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppUser extends BaseEntity {

    @Id
    @Column
    Long id;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    String email;

    @Column
    String password;

    @Column
    String phone;

    @Column
    Boolean isSignedIn;

    @Column
    String accessToken;

    @Column
    String tokenSecret;

    @Column
    String oauthVerifier;
}
