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
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString.Exclude;

@Entity
@Table
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String nom;
    protected String commentaire;
    @Column(length = 100000)
    protected List<String> photos= new ArrayList<>();
    protected String videos;
    protected double depense;
	
	@ManyToOne
	@JoinColumn(name="idVille")
	@JsonIgnore
	@Exclude
	private Ville ville;
	
	@ManyToOne
	@JoinColumn(name="idExperience")
	@JsonIgnore
	@Exclude
	private Experiences experience;
	
	@ManyToMany
	@JoinTable(name="Activite_guideVoyage",
	joinColumns = @JoinColumn(name="idActivité"), 
	inverseJoinColumns = @JoinColumn(name="idGuideVoyage"))
	@Exclude
	@JsonIgnore
	private List<GuideVoyage> listeG;

	public Activite(int id, String nom, String commentaire, List<String> photos, String videos, double depense) {
		super();
		this.id = id;
		this.nom = nom;
		this.commentaire = commentaire;
		this.photos = photos;
		this.videos = videos;
		this.depense = depense;
	}
}

