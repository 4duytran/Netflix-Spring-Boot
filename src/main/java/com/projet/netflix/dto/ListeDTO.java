package com.projet.netflix.dto;



import com.projet.netflix.entities.Session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //alternative a l'opérateur new
public class ListeDTO {

	private Long idFilmDTO;


}
