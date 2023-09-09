package com.projet.netflix.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.netflix.dto.SessionDTO;
import com.projet.netflix.entities.Session;
import com.projet.netflix.repos.SessionRepository;

@Service
public class SessionServiceImpl implements SessionService{
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public List<Session> findAllSessionByUtilisateurId(Long id) {
		// TODO Auto-generated method stub						// comment AUTOGENERER???????
		return sessionRepository.findAllSessionByUtilisateurId(id);
	}

	
	//SUPP si pas besoin
		@Override
		public SessionDTO convertEntityToDto(Session session) {
			
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);//afficher nomCat 
			SessionDTO sessionDTO = modelMapper.map(session, SessionDTO.class);
			
			return sessionDTO;
		}
		@Override
		public Session convertDtoToEntity(SessionDTO sessionDto) {
			
			Session session = new Session();
			session = modelMapper.map(sessionDto,Session.class);
			return session;
	}

}
