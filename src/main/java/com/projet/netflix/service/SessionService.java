package com.projet.netflix.service;

import java.util.List;

import com.projet.netflix.dto.SessionDTO;
import com.projet.netflix.entities.Session;
import com.projet.netflix.entities.Utilisateur;

public interface SessionService {

	
	public List<Session> findAllSessionByUtilisateurId(Long id);
	
	public SessionDTO convertEntityToDto(Session session);
	public Session convertDtoToEntity(SessionDTO sessionDto);
}
