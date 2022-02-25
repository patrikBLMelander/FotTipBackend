package com.fotboll.tips.config;

import com.fotboll.tips.config.security.Role;
import com.fotboll.tips.model.AppUser;
import com.fotboll.tips.repository.AppUserRepository;
import com.fotboll.tips.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Slf4j
@Configuration
public class BootstrapConfig {

    @Bean
    public CommandLineRunner bootstrap(AppUserRepository appUserRepository,
                                       RoleRepository roleRepo,
                                       PasswordEncoder encoder) {
        return (args -> {
            if (!appUserRepository.existsByEmail("admin@email.com")) {
                var superAdminRole = roleRepo.saveAndFlush(new Role(Role.RoleConstant.SUPER_ADMIN));
                var adminRole = roleRepo.saveAndFlush(new Role(Role.RoleConstant.ADMIN));
                var candidateRole = roleRepo.saveAndFlush(new Role(Role.RoleConstant.USER));

                AppUser admin = new AppUser();
                admin.setFirstName("Patrik");
                admin.setLastName("Melander");
                admin.setEmail("admin@email.com");
                admin.setRoleList(List.of(superAdminRole, adminRole, candidateRole));
                admin.setPassword(encoder.encode("admin1234"));
                admin.setIsAdmin(true);

                appUserRepository.saveAndFlush(admin);

                log.info("Added SUPER_ADMIN to the application {}", admin);
            }
        });
    }
}

