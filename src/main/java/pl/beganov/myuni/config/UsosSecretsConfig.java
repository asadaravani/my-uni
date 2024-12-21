package pl.beganov.myuni.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource("classpath:secret.yml")
@NoArgsConstructor
public class UsosSecretsConfig {

    @Value("${api.key}")
    String key;

    @Value("${api.secret}")
    String secret;

}
