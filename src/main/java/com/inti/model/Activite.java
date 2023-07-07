package com.inti.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Table
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Activite {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String commentaire;
	private String photos;
	private String videos;
	private double depense;
	
	@ManyToOne
	@JoinColumn(name="idVille")
	@JsonIgnore
	private Ville ville;
	
	@ManyToOne
	@JoinColumn(name="idExperience")
	@JsonIgnore
	private Experiences experience;
	
	@ManyToMany
	@JoinTable(name="Activite_guideVoyage",
	joinColumns = @JoinColumn(name="idActivité"), 
	inverseJoinColumns = @JoinColumn(name="idGuideVoyage"))
	@JsonIgnore
	private List<GuideVoyage> listeG;
}
