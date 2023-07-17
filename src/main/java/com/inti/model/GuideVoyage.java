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

@Entity // Indique qu'il s'agit d'une entité
@Table // Définit la table correspondante dans la base de données
@Data // Génère automatiquement les getters, setters et d'autres méthodes utilitaires
@AllArgsConstructor // Génère un constructeur avec tous les paramètres
@NoArgsConstructor // Génère un constructeur sans paramètre
@JsonIgnoreProperties({"hibernateLazyInitializer"})// Ignore la propriété "hibernateLazyInitializer" lors de la sérialisation/désérialisation JSON
public class GuideVoyage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int idGuide; // Identifiant du guide de voyage

	private String nom; // Nom du guide de voyage

	private LocalDate dateCreation; // Date de création du guide de voyage

	private String description; // Description du guide de voyage

	private boolean estApprouve; // Statut d'approbation du guide de voyage

	private double note; // Note du guide de voyage

	private int nbrDeNote; // Nombre de notes reçues par le guide de voyage

	private int sommeNoteTot; // Somme totale des notes du guide de voyage

	@ManyToMany
	@JoinTable(
		name="guideVoyage_utilisateur", // Nom de la table de jointure
		joinColumns = @JoinColumn(name="idGuideVoyage"), // Clé étrangère dans la table de jointure
		inverseJoinColumns = @JoinColumn(name="idUtilisateur") // Clé étrangère de l'autre entité dans la table de jointure
	)
	@Exclude // Exclut cette relation lors de la sérialisation JSON
	private List<Utilisateur> listeU; // Liste des utilisateurs associés au guide de voyage

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
	@JoinTable(
		name="activite_guideVoyage", // Nom de la table de jointure
		joinColumns = @JoinColumn(name="idGuideVoyage"), // Clé étrangère dans la table de jointure
		inverseJoinColumns = @JoinColumn(name="idActivité") // Clé étrangère de l'autre entité dans la table de jointure
	)
	@Exclude // Exclut cette relation lors de la sérialisation JSON
	private List<Activite> activites; // Liste des activités liées au guide de voyage

	// Constructeur avec tous les paramètres
	public GuideVoyage(String nom, LocalDate dateCreation, String description, boolean estApprouve, List<Activite> activites) {
		super();
		this.nom = nom;
		this.dateCreation = dateCreation;
		this.description = description;
		this.estApprouve = estApprouve;
		this.activites = activites;
	}

	public GuideVoyage(String nom, LocalDate dateCreation, String description, boolean estApprouve) {
		super();
		this.nom = nom;
		this.dateCreation = dateCreation;
		this.description = description;
		this.estApprouve = estApprouve;
	}

	
	
	
}
