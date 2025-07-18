package com.java.spring_boot_camp.configs;

import com.java.spring_boot_camp.common.enums.Role;
import com.java.spring_boot_camp.modules.users.entities.User;
import com.java.spring_boot_camp.modules.users.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor // inject container and constructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AppConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());

//                String rawPassword = "AdMin@2024!";
                User user = User.builder()
                        .username("admin")
//                        .password(this.passwordEncoder.encode(rawPassword))
                        .roles(roles)
                        .build();

                userRepository.save(user);

                log.warn("Admin is created with default value");
            } else {
                log.warn("Admin is existing!!");
            }

        };
    }
}
