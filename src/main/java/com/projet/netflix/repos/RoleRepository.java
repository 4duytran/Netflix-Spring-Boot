package com.projet.netflix.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.netflix.entities.ERole;
import com.projet.netflix.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(ERole name);

}
