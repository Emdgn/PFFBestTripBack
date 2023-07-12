package com.inti.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Entity @Table @Data 
@AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class GuideVoyage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGuide;
	private String nom;
	private LocalDate dateCreation;
	private String description;
	
	@ManyToMany
	@JoinTable(name="guideVoyage_utilisateur",
	joinColumns = @JoinColumn(name="idGuideVoyage"), 
	inverseJoinColumns = @JoinColumn(name="idUtilisateur"))
	@Exclude
	private List<Utilisateur> listeU;
	
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST} ) 
	@JoinTable(name="activite_guideVoyage",
	joinColumns = @JoinColumn(name="idGuideVoyage"), 
	inverseJoinColumns = @JoinColumn(name="idActivité"))
    @Exclude
	private List<Activite> activites;
	

	public GuideVoyage(String nom, LocalDate dateCreation, String description, List<Activite> activites) {
		super();
		this.nom = nom;
		this.dateCreation = dateCreation;
		this.description = description;
		this.activites = activites;
		}

	public GuideVoyage(String nom, LocalDate dateCreation, String description) {
		super();
		this.nom = nom;
		this.dateCreation = dateCreation;
		this.description = description;
	}



	
	
	
	
	
	

}
