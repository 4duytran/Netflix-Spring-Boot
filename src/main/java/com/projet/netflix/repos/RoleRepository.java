package com.projet.netflix.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.netflix.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByRole(String role);

}
