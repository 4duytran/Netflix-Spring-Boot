package com.projet.netflix.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
//Lombok
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
		    private String lastName;
		    
		    @NonNull // Lombok
		    // Contraintes de taille
		    @Size(min = 6, max = 32)
		    private String firstName;

		    @NonNull // Lombok
		    private String password;

		    @Transient // Non enregistré dans la BD
		    private String passwordConfirm;

		    @NonNull // Lombok
		    private String age;
		    
		    @NonNull
		    @Enumerated(EnumType.STRING)
		    private Role role;
		    
		    @OneToMany (mappedBy = "utilisateur")
			@JsonIgnore //éviter la bloucle infini
			private List<Session> session;
		   
		    

	//JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT JWT//
		    
		    
		    @Override
		    public String getPassword() {
		        return password;
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
		        return List.of(new SimpleGrantedAuthority(role.name()));
		    }
		    
		    


}
