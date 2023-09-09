package com.projet.netflix.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
//Lombok
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Session {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idSession;
	 
	 @NonNull // Lombok
	 // Contraintes de taille
	 @Size(min = 6, max = 32)
	 private String nomSession;
	 
	@ManyToOne
	private Utilisateur utilisateur;
	
}
