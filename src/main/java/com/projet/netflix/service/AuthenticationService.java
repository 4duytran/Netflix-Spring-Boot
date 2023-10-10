package com.projet.netflix.service;

import java.util.Set;
import java.util.HashSet;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.netflix.dto.JwtAuthenticationResponse;
import com.projet.netflix.dto.SignInRequest;
import com.projet.netflix.dto.SignUpRequest;
import com.projet.netflix.entities.Role;
import com.projet.netflix.entities.Utilisateur;
import com.projet.netflix.repos.UtilisateurRepository;

@Service
public class AuthenticationService {

	private UtilisateurRepository userRepository;
	  private UtilisateurService userService;
	  private PasswordEncoder passwordEncoder;
	  private JwtService jwtService;
	  private AuthenticationManager authenticationManager;

	  public JwtAuthenticationResponse signup(SignUpRequest request) {
		  

	      var user = Utilisateur
	                  .builder()
	                  .firstName(request.getFirstName())
	                  .lastName(request.getLastName())
	                  .email(request.getEmail())
	                  .age(request.getAge())
	                  .password(passwordEncoder.encode(request.getPassword()))
	                  .role(Role.ROLE_USER)
	                  .build();

	      user = userService.saveUtilisateur(user);
	      var jwt = jwtService.generateToken(user);
	      return JwtAuthenticationResponse.builder().token(jwt).build();
	  }


	  public JwtAuthenticationResponse signin(SignInRequest request) {
	      authenticationManager.authenticate(
	              new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
	      var user = userRepository.findByEmail(request.getEmail())
	              .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
	      var jwt = jwtService.generateToken(user);
	      return JwtAuthenticationResponse.builder().token(jwt).build();
	  }

}
