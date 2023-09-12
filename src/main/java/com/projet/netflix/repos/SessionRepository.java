package com.projet.netflix.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.projet.netflix.entities.Session;

@Repository
@RepositoryRestResource(collectionResourceRel = "session",path = "session")
public interface SessionRepository extends JpaRepository<Session, Long> {

	
	//SELECT * FROM session WHERE utilisateur_id_utilisateur = 1;
	
	@Query("SELECT s FROM Session s WHERE s.utilisateur.idUtilisateur = :utilisateurId")
	List<Session> findAllSessionByUtilisateurId(@Param("utilisateurId") Long Id);
	
	//SELECT s FROM Session s WHERE s.utilisateur.idUtilisateur = :utilisateurId
	//"select p from Produit p where p.categorie = ?1"
	
}
