package com.projet.netflix.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projet.netflix.dto.UtilisateurDTO;
import com.projet.netflix.entities.Utilisateur;
import com.projet.netflix.service.UtilisateurService;


@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurRESTController {
	
	@Autowired
	UtilisateurService utilisateurService;

	@RequestMapping(method=RequestMethod.GET)
	List<UtilisateurDTO> getAllUtilisateurs()
	{
		return utilisateurService.getAllUtilisateurs();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public UtilisateurDTO getUtilisateurById(@PathVariable("id") Long id)
	 {
		return utilisateurService.getUtilisateur(id);
	 }
	
	@RequestMapping(method = RequestMethod.POST)
	public UtilisateurDTO createUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) 
	{
	return utilisateurService.saveUtilisateur(utilisateurDTO);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public UtilisateurDTO updateUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) 
	{
	return utilisateurService.updateUtilisateur(utilisateurDTO);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deleteUtilisateur(@PathVariable("id") Long id)
	{
		utilisateurService.deleteUtilisateurById(id);
	}
	

}
