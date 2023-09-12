package com.projet.netflix.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.projet.netflix.entities.Utilisateur;

@Repository
@RepositoryRestResource(collectionResourceRel = "rest",path = "rest")
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

	Optional<Utilisateur> findByEmail(String email);
	
	List<Utilisateur> findByIdUtilisateur(Long id);
	List<Utilisateur> findByNomUtilisateur(String nom);
	List<Utilisateur> findByNomUtilisateurContains(String nom);
	List<Utilisateur> findByOrderByNomUtilisateurAsc();


}
