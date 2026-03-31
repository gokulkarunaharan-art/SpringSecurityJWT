package com.gokul.SpringBasicSecurityDemoPractice.Bootstrap;

import com.gokul.SpringBasicSecurityDemoPractice.model.User;
import com.gokul.SpringBasicSecurityDemoPractice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataPopulator {
    @Bean
    public CommandLineRunner populateUserDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            User user = User.builder()
                    .name("gokul")
                    .password(passwordEncoder.encode("1234"))
                    .role("admin")
                    .build();
            userRepository.save(user);
        };
    }
}
