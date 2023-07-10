package com.inti.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@OneToMany (mappedBy = "experience")
	@JsonIgnore
	private List<Activite>activite;
	
	@ManyToOne
	@JoinColumn(name = "idUtilisateur")
	@JsonIgnore
	private Utilisateur utilisateur;

	public Experiences(String nom, LocalDate dateDebut, LocalDate dateFin, String type) {
		super();
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
	}
	
	

}
