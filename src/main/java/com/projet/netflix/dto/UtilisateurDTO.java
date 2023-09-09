package com.projet.netflix.dto;

import java.util.Date;

import com.projet.netflix.entities.Session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //alternative a l'opérateur new
public class UtilisateurDTO {
	private Long idUser;
	private String emailUser;
	private String nomUser;
	private String prenomUser;
	private String passwordUser;
	private String confirmPasswordUser;
	
	private Session session;

}
