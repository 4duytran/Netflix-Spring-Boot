package com.projet.netflix.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Utilisateur implements UserDetails, Serializable{

			private static final long serialVersionUID = 1L;
	
		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long idUtilisateur;

		    @NonNull // Lombok
		    @Email // Doit avoir la forme d'une adresse email
		    private String email;
		    
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
		   
		    

	//JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT//
		    
		    
		    @Override
		    public String getPassword() {
		        return motDePasse;
		    }

		    @Override
		    public String getUsername() {
		        return email; // Utilisez l'email comme nom d'utilisateur
		    }
		    
		    @Override
		    public boolean isAccountNonExpired() {
		        return true;
		    }

		    @Override
		    public boolean isAccountNonLocked() {
		        return true;
		    }

		    @Override
		    public boolean isCredentialsNonExpired() {
		        return true;
		    }

		    @Override
		    public boolean isEnabled() {
		        return true;
		    }
		    
		    @Override
		    public Collection<? extends GrantedAuthority> getAuthorities() {
		        return roles.stream()
		                    .map(role -> new SimpleGrantedAuthority(role.getName().name()))
		                    .collect(Collectors.toList());
		    }
		    
		    
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

}
