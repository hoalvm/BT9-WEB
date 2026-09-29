package vn.iotstar.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.repository.RoleRepository;
import vn.iotstar.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            // Idempotent: only insert if not already present
            Role userRole = roleRepository.findByNameIgnoreCase("USER")
                    .orElseGet(() -> roleRepository.save(new Role("USER")));

            roleRepository.findByNameIgnoreCase("ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role("ADMIN")));

            // Seed test user
            if (!userRepository.existsByEmailIgnoreCase("lyvomyhoa@gmail.com")) {
                User testUser = new User();
                testUser.setEmail("lyvomyhoa@gmail.com");
                testUser.setFullName("Lý Võ Mỹ Hoa");
                testUser.setPassword(passwordEncoder.encode("123456"));
                testUser.setRole(userRole);
                testUser.setEnabled(true);
                userRepository.save(testUser);
            }
        };
    }
}
