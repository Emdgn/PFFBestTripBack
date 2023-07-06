package com.inti.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table @Data 
@AllArgsConstructor @NoArgsConstructor
public class GuideVoyage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGuide;
	private String nom;
	private LocalDate dateCreation;
	private String description;
//	private List<Utilisateur> listeU;
//	private List<Activite> listeA;
	
	public GuideVoyage(String nom, LocalDate dateCreation, String description) {
		super();
		this.nom = nom;
		this.dateCreation = dateCreation;
		this.description = description;
	}
	
	

}
