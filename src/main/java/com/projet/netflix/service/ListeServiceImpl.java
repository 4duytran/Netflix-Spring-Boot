package com.projet.netflix.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.netflix.dto.ListeDTO;
import com.projet.netflix.dto.UtilisateurDTO;
import com.projet.netflix.entities.Maliste;
import com.projet.netflix.entities.Utilisateur;
import com.projet.netflix.repos.MalisteRepository;

@Service
public class ListeServiceImpl implements ListeService{
	
	@Autowired
	MalisteRepository malisteRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	
	@Override
	public ListeDTO saveIdFilm(ListeDTO l) {
		return convertEntityToDto(malisteRepository.save(convertDtoToEntity(l)));
	}
	
	@Override
	public ListeDTO getIdFilm(Long idFilm) {
		Optional<Maliste> liste = malisteRepository.findByIdFilm(idFilm);
		return liste.map(this::convertEntityToDto).orElse(null);
	}
	@Override
	public List<ListeDTO> getAllFilms() {
	return malisteRepository.findAll().stream()//java fonctional programming = créer un code performant et concis
			.map(this::convertEntityToDto)
			.collect(Collectors.toList());
	}
	@Override
	public Optional<Maliste> findByIdFilm(Long idFilm) {
	    return malisteRepository.findByIdFilm(idFilm);
	}
	@Override
	public void deleteFilmByIdFilm(Long idFilm) {
		Optional<Maliste> liste = malisteRepository.findByIdFilm(idFilm);
	    if (liste.isPresent()) {
	        malisteRepository.delete(liste.get());
	    }
	}

	
	@Override
	public ListeDTO convertEntityToDto(Maliste liste) {
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);//afficher nomCat 
		ListeDTO listeDTO = modelMapper.map(liste, ListeDTO.class);
		
		return listeDTO;
		

	}
	
	@Override
	public Maliste convertDtoToEntity(ListeDTO listeDto) {
		
		Maliste liste = new Maliste();
		liste = modelMapper.map(listeDto,Maliste.class);
		return liste;
		
	}

}
