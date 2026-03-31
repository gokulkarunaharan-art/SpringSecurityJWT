package com.gokul.SpringBasicSecurityDemoPractice.Bootstrap;

import com.gokul.SpringBasicSecurityDemoPractice.model.Role;
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
                    .name("admin")
                    .password(passwordEncoder.encode("1234"))
                    .role(Role.ADMIN)
                    .build();
            User user1 = User.builder()
                    .name("user")
                    .password(passwordEncoder.encode("1234"))
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
            userRepository.save(user1);
        };
    }
}
