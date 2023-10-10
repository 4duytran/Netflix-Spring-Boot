package com.projet.netflix.service;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.projet.netflix.dto.UtilisateurDTO;
import com.projet.netflix.entities.Session;
import com.projet.netflix.entities.Utilisateur;


public interface UtilisateurService {
	
	UserDetailsService userDetailsService();
	UserDetails loadUserByUsername(String email);
	Utilisateur saveUtilisateur(Utilisateur u);
	UtilisateurDTO getUtilisateur(Long id);
	List<UtilisateurDTO> getAllUtilisateurs();
	
	UtilisateurDTO updateUtilisateur(UtilisateurDTO u);
	void deleteUtilisateur(Utilisateur u);
	void deleteUtilisateurById(Long id);
	
	List<Utilisateur> findByNomUtilisateur(String nom);
	List<Utilisateur> findByNomUtilisateurContains(String nom);
	List<Utilisateur> findByOrderByNomUtilisateurAsc();
	
	UtilisateurDTO convertEntityToDto(Utilisateur utilisateur);
	Utilisateur convertDtoToEntity(UtilisateurDTO utilisateurDto);
	

}
