package com.projet.netflix.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.netflix.dto.ListeDTO;
import com.projet.netflix.entities.Maliste;
import com.projet.netflix.service.ListeService;

@RestController
@RequestMapping("/rest/maliste")
@CrossOrigin(origins = "http://localhost:4200")
public class ListRestController {

	 @Autowired
	    private ListeService listeService;

	 @PostMapping("/add")
	    public ResponseEntity<ListeDTO> addIdFilm(@RequestBody ListeDTO listeDto) {
	        try {
	            ListeDTO savedFilm = listeService.saveIdFilm(listeDto);
	            return new ResponseEntity<>(savedFilm, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @GetMapping
	    public List<ListeDTO> getAllIdFilms() {
	        return listeService.getAllFilms();
	    }

	    @GetMapping("/{id}")
	    public ListeDTO getIdFilm(@PathVariable("id") Long id) {
	        return listeService.getIdFilm(id);
	    }

	    @PutMapping
	    public ResponseEntity<ListeDTO> updateIdFilm(@RequestBody ListeDTO listeDto) {
	        try {
	            ListeDTO updatedFilm = listeService.saveIdFilm(listeDto);
	            return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<HttpStatus> deleteIdFilm(@PathVariable("id") Long idFilm) {
	        try {
	            listeService.deleteFilmByIdFilm(idFilm);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
}

