package com.projet.netflix.restcontrollers;

import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projet.netflix.dto.JwtAuthenticationResponse;
import com.projet.netflix.dto.SignInRequest;
import com.projet.netflix.dto.SignUpRequest;
import com.projet.netflix.dto.UtilisateurDTO;
import com.projet.netflix.entities.Utilisateur;
import com.projet.netflix.service.AuthenticationService;
import com.projet.netflix.service.UtilisateurService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class UtilisateurRESTController {

    private final AuthenticationService authenticationService;
	private final UtilisateurService utilisateurService;

	
    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }
	
//    @PostMapping("/signup")
//    public ResponseEntity<Utilisateur> registerUser(@RequestBody Utilisateur utilisateur) {
//        try {
//            Utilisateur savedUser = utilisateurService.saveUtilisateur(utilisateur);//utilisateurDTO
//            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

	
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
	public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) //utilisateurDTO
	{
	return utilisateurService.saveUtilisateur(utilisateur);//utilisateurDTO
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
