package com.projet.netflix.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Maliste {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idListe;

	@NonNull
	private Long idFilm;

	// Relation avec Session
	@ManyToOne
	private Session session;
}
