package com.inti.model;

import java.time.LocalDate;
import java.util.List;

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
import lombok.ToString.Exclude;

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
	private boolean estApprouvee;
	
	@OneToMany (mappedBy = "experience")
	@Exclude
	private List<Activite> activites;
	
	@ManyToOne
	@JoinColumn(name = "idUtilisateur")
	@Exclude
	private Utilisateur utilisateur;

	public Experiences(String nom, LocalDate dateDebut, LocalDate dateFin, String type,
			boolean estApprouvee, List<Activite> activites) {
		super();
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
		this.estApprouvee = estApprouvee;
		this.activites = activites;
	}
	
	
	public Experiences(String nom, LocalDate dateDebut, LocalDate dateFin, String type,
			boolean estApprouvee) {
		super();
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
		this.estApprouvee = estApprouvee;
	}
	

	public Experiences(int idExperience, String nom, LocalDate dateDebut, LocalDate dateFin, String type,
			boolean estApprouvee) {
		super();
		this.idExperience = idExperience;
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
		this.estApprouvee = estApprouvee;
	}


	public Experiences(String nom, LocalDate dateDebut, LocalDate dateFin, String type) {
		super();
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.type = type;
	}

	
	


		
	
	
}
