package com.projet.netflix.service;

import java.util.List;
import java.util.Optional;

import com.projet.netflix.dto.ListeDTO;
import com.projet.netflix.entities.Maliste;


public interface ListeService {
	ListeDTO saveIdFilm(ListeDTO l);
	ListeDTO getIdFilm(Long id);
	List<ListeDTO> getAllFilms();
	Optional<Maliste> findByIdFilm(Long idFilm);
	void deleteFilmByIdFilm(Long idFilm);
	
	ListeDTO convertEntityToDto(Maliste liste);
	Maliste convertDtoToEntity(ListeDTO listeDto);

}
