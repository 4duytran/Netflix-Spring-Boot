package com.projet.netflix.config;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.projet.netflix.entities.Role;
import com.projet.netflix.entities.Utilisateur;
import com.projet.netflix.repos.UtilisateurRepository;
import com.projet.netflix.service.UtilisateurService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UtilisateurRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UtilisateurService userService;
    


    @Override
    public void run(String... args) throws Exception {
        
        if (userRepository.count() == 0) {

  

            Utilisateur admin = Utilisateur
                .builder()
                .firstName("adminnnn")
                .lastName("adminnnn")
                .email("admin@admin.com")
                .age("18")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_ADMIN)
                .build();

            userService.saveUtilisateur(admin);
            log.debug("created ADMIN user - {}", admin);
        }
    }

}