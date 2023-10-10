package com.projet.netflix.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.netflix.dto.UtilisateurDTO;
import com.projet.netflix.entities.Utilisateur;
import com.projet.netflix.repos.SessionRepository;
import com.projet.netflix.repos.UtilisateurRepository;
import com.projet.netflix.dto.SessionDTO;
import com.projet.netflix.entities.Role;
import com.projet.netflix.entities.Session;

//@Transactional
@Service
public class UtilisateurServiceImpl implements UtilisateurService,UserDetailsService{
	
	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	@Autowired
	SessionRepository sessionRepository;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	  public UserDetailsService userDetailsService() {
	      return new UserDetailsService() {
	          @Override
	          public UserDetails loadUserByUsername(String username) {
	              return utilisateurRepository.findByEmail(username)
	                      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	          }
	      };
	  }
	  
      @Override
      public UserDetails loadUserByUsername(String username) {
          return utilisateurRepository.findByEmail(username)
                  .orElseThrow(() -> new UsernameNotFoundException("User not found"));
      }

	  
	@Override
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
	   

	    // Vérifier si l'email est déjà utilisé
	    if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
	        throw new RuntimeException("Email déjà utilisé!");
	    }

	    // Modifier le mot de passe
	    utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));


	    // Attribuer un rôle par défaut à l'utilisateur
	    utilisateur.setRole(Role.ROLE_USER);

	    // Sauvegarder l'utilisateur
	    Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);

	    // Créer une nouvelle instance de Session
	    Session newSession = new Session();

	    // Définir le nom de la session avec le prénom de l'utilisateur
	    newSession.setNomSession(savedUtilisateur.getFirstName());

	    // Associer la session à l'utilisateur sauvegardé
	    newSession.setUtilisateur(savedUtilisateur);

	    // Sauvegarder la session dans la base de données
	    sessionRepository.save(newSession);

	    // Convertir l'entité utilisateur en DTO pour le retour
	    return savedUtilisateur;
	}
//    @Override
//    public UtilisateurDTO saveUtilisateur(UtilisateurDTO u) {
//        // Convertir le DTO en entité et sauvegarder l'utilisateur
//        Utilisateur savedUtilisateur = utilisateurRepository.save(convertDtoToEntity(u));
//
//        // Créer une nouvelle instance de Session
//        Session newSession = new Session();
//        
//        // Définir le nom de la session avec le prénom de l'utilisateur
//        newSession.setNomSession(savedUtilisateur.getPrenomUtilisateur());
//        
//        // Associer la session à l'utilisateur sauvegardé
//        newSession.setUtilisateur(savedUtilisateur);
//        
//        // Sauvegarder la session dans la base de données
//        sessionRepository.save(newSession);
//        
//        // Convertir l'entité utilisateur en DTO pour le retour
//        return convertEntityToDto(savedUtilisateur);
//    }
	
//	@Override
//	public UtilisateurDTO saveUtilisateur(UtilisateurDTO u) {
//	return convertEntityToDto(utilisateurRepository.save(convertDtoToEntity(u)));
//	}
	@Override
	public UtilisateurDTO updateUtilisateur(UtilisateurDTO u) {
	return convertEntityToDto(utilisateurRepository.save(convertDtoToEntity(u)));
	}
	@Override
	public void deleteUtilisateur(Utilisateur u) {
		utilisateurRepository.delete(u);
	}
	@Override
	public void deleteUtilisateurById(Long id) {
		 utilisateurRepository.deleteById(id);
	}
	@Override
	public UtilisateurDTO getUtilisateur(Long id) {
	return convertEntityToDto(utilisateurRepository.findById(id).get());
	}
	@Override
	public List<UtilisateurDTO> getAllUtilisateurs() {
	return utilisateurRepository.findAll().stream()//java fonctional programming = créer un code performant et concis
			.map(this::convertEntityToDto)
			.collect(Collectors.toList());
		
		/*List<Produit> prods = produitRepository.findAll();
		List<ProduitDTO> listprodDto = new ArrayList<>(prods.size());
		for (Produit p : prods)
		listprodDto.add(convertEntityToDto(p));
		return listprodDto;*/
	}
	
	@Override
	public List<Utilisateur> findByNomUtilisateur(String nom) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findByLastName(nom);
	}
	@Override
	public List<Utilisateur> findByNomUtilisateurContains(String nom) {
		return utilisateurRepository.findByLastNameContains(nom);
	}
	@Override
	public List<Utilisateur> findByOrderByNomUtilisateurAsc() {
		// TODO Auto-generated method stub
		return utilisateurRepository.findByOrderByLastNameAsc();
	}
	
	@Override
	public UtilisateurDTO convertEntityToDto(Utilisateur utilisateur) {
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);//afficher nomCat 
		UtilisateurDTO utilisateurDTO = modelMapper.map(utilisateur, UtilisateurDTO.class);
		
		return utilisateurDTO;
		
		/*ProduitDTO produitDTO = new ProduitDTO();
		
		produitDTO.setIdProduit(p.getIdProduit());
		produitDTO.setNomProduit(p.getNomProduit());
		produitDTO.setPrixProduit(p.getPrixProduit());
		produitDTO.setCategorie(p.getCategorie());
		
		return produitDTO;*/
		
		/*return ProduitDTO.builder()
				.idProduit(p.getIdProduit())
				.nomProduit(p.getNomProduit())
				//.prixProduit(p.getPrixProduit())
				.dateCreation(p.getDateCreation())
				.nomCat(p.getCategorie().getNomCat()) //attention si categorie null alors erreur
				//.categorie(p.getCategorie())
				.build();*/
	}
	
	@Override
	public Utilisateur convertDtoToEntity(UtilisateurDTO utilisateurDto) {
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = modelMapper.map(utilisateurDto,Utilisateur.class);
		return utilisateur;
		
		/*Produit produit = new Produit();
		
		produit.setIdProduit(produitDto.getIdProduit());
		produit.setNomProduit(produitDto.getNomProduit());
		produit.setPrixProduit(produitDto.getPrixProduit());
		produit.setDateCreation(produitDto.getDateCreation());
		produit.setCategorie(produitDto.getCategorie());
		
		 return produit;*/
	}
	

}
