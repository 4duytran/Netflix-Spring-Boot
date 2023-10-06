package com.projet.netflix.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projet.netflix.entities.Maliste;
import com.projet.netflix.entities.Utilisateur;

@Repository
public interface MalisteRepository extends JpaRepository<Maliste, Long>{


	@Query("SELECT m FROM Maliste m WHERE m.idFilm = :idFilm")
	Optional<Maliste> findByIdFilm(@Param("idFilm") Long idFilm);
}
