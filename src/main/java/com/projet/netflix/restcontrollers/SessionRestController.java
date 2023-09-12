package com.projet.netflix.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projet.netflix.entities.Session;
import com.projet.netflix.service.SessionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/") 
public class SessionRestController {
	
	@Autowired // fait pour L'injection de dépandances -> ici c'est pour generer une instance demandder a gpt pour plus de details (c'est pas assez)
	SessionService sessionService;

	@RequestMapping(value="/session/{id}",method = RequestMethod.GET)
	public List<Session> findAllSessionByUtilisateurId(@PathVariable("id") Long id) 
	{
	return sessionService.findAllSessionByUtilisateurId(id);
	}
}
