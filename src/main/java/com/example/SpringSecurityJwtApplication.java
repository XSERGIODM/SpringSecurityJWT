package com.example;

import com.example.models.Model_Role;
import com.example.models.Model_User;
import com.example.repositories.Repository_User;
import com.example.util.Util_ERole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    PasswordEncoder passwordEncoder;
    Repository_User repositoryUser;
    @Bean
    CommandLineRunner init() {
        return args -> {

           /* Model_User modelUser = Model_User.builder()
                    .username("danilo")
                    .password(passwordEncoder.encode("1234"))
                    .email("checho@gmail.com")
                    .roles(Set.of(Model_Role.builder()
                            .name(Util_ERole.valueOf(Util_ERole.ADMIN.name()))
                            .build()))
                    .build();
            repositoryUser.save(modelUser);

            Model_User modelUser2 = Model_User.builder()
                    .username("cele")
                    .password(passwordEncoder.encode("1234"))
                    .email("cele@gmail.com")
                    .roles(Set.of(Model_Role.builder()
                            .name(Util_ERole.valueOf(Util_ERole.INVITED.name()))
                            .build()))
                    .build();
            repositoryUser.save(modelUser2);

            Model_User modelUser3 = Model_User.builder()
                    .username("pili")
                    .password(passwordEncoder.encode("1234"))
                    .email("pili@gmail.com")
                    .roles(Set.of(Model_Role.builder()
                            .name(Util_ERole.valueOf(Util_ERole.USER.name()))
                            .build()))
                    .build();
            repositoryUser.save(modelUser3);*/
        };
    }
}
