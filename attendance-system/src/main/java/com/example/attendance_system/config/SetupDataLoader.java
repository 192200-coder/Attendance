package com.example.attendance_system.config;

import com.example.attendance_system.entity.User;
import com.example.attendance_system.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SetupDataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // CREAR ADMIN
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123")); // Contraseña: admin123
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }

            // CREAR PROFESOR
            if (userRepository.findByUsername("profesor1").isEmpty()) {
                User prof = new User();
                prof.setUsername("profesor1");
                prof.setPassword(passwordEncoder.encode("password123")); // Contraseña: password123
                prof.setRole("USER");
                userRepository.save(prof);
            }

            System.out.println("Base de datos sincronizada con usuarios encriptados.");
        };
    }
}