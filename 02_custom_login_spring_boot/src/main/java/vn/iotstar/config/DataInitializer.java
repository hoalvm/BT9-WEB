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
    CommandLineRunner init(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            Role userRole = roleRepository
                    .findByName("ROLE_USER")
                    .orElseGet(() ->
                        roleRepository.save(
                            Role.builder()
                                .name("ROLE_USER")
                                .build()
                        )
                    );

            Role adminRole = roleRepository
                    .findByName("ROLE_ADMIN")
                    .orElseGet(() ->
                        roleRepository.save(
                            Role.builder()
                                .name("ROLE_ADMIN")
                                .build()
                        )
                    );

            // Initialize or update user01 per 02_custom_login_spring_boot4.md
            User user01 = userRepository.findByUsername("user01")
                    .or(() -> userRepository.findByEmail("user01@gmail.com"))
                    .orElse(null);

            if (user01 == null) {
                user01 = User.builder()
                        .username("user01")
                        .email("user01@gmail.com")
                        .password(passwordEncoder.encode("123456"))
                        .fullName("Nguyễn Hữu Trung")
                        .images("/images/user.png")
                        .role(userRole)
                        .enabled(true)
                        .build();
                userRepository.save(user01);
            } else {
                user01.setUsername("user01");
                user01.setEmail("user01@gmail.com");
                user01.setFullName("Nguyễn Hữu Trung");
                user01.setImages("/images/user.png");
                user01.setRole(userRole);
                user01.setEnabled(true);
                userRepository.save(user01);
            }
        };
    }
}
