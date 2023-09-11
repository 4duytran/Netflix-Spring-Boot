package com.projet.netflix.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
//Lombok
@Getter
@Setter
//@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Utilisateur {

		
		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long idUtilisateur;

		    @NonNull // Lombok
		    @Email // Doit avoir la forme d'une adresse email
		    private String email;
		    
		    @NonNull
		    private String username;
		    
		    @NonNull // Lombok
		    // Contraintes de taille
		    @Size(min = 6, max = 32)
		    private String nomUtilisateur;
		    
		    @NonNull // Lombok
		    // Contraintes de taille
		    @Size(min = 6, max = 32)
		    private String prenomUtilisateur;

		    @NonNull // Lombok
		    private String motDePasse;

		    @Transient // Non enregistré dans la BD
		    private String confirmationMotDePasse;

		    @NonNull // Lombok
		    private Short age;
		    
		    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
		    @JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id") ,
		     inverseJoinColumns = @JoinColumn(name="role_id"))
		    private List<Role> roles; 
		    
		    
		    @OneToMany (mappedBy = "utilisateur")
			@JsonIgnore //éviter la bloucle infini
			private List<Session> session;
		   
		    

	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

}
