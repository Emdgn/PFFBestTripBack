package com.inti.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity @Table
@Data @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	@NonNull
	private String username;
	@Column(nullable = false)
	@NonNull
	private String mdp;
	@Column(nullable = false)
	@NonNull
	private boolean estAbonne;
	@Column(nullable = false)
	@NonNull
	private String role;
	@Column(nullable = false)
	@NonNull
	private String email;
	private String nom;
	private String prenom;

	@ManyToMany
	@JoinTable(name="guideVoyage_utilisateur",
	joinColumns = @JoinColumn(name="idUtilisateur"), 
	inverseJoinColumns = @JoinColumn(name="idGuideVoyage"))
	@JsonIgnore
	private List<GuideVoyage> listeG = new ArrayList<>();
	
	@OneToMany(mappedBy = "utilisateur")
	@JsonIgnore
	private List<Experiences> listeE;
	
}
