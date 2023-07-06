package com.inti.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table
@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Experiences {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idExperience;
	private String nom;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String type;
	
	@ManyToMany
	@JsonIgnore
	private List<Activite>activite;
	
	@OneToMany
	@JsonIgnore
	private List<Utilisateur> utilisateur;

}
