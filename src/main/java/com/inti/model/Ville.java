package com.inti.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table
@Data @AllArgsConstructor @NoArgsConstructor
public class Ville {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String nom;
	
	@ManyToOne
	@JoinColumn(name = "idPays")
	private Pays pays;

	@OneToMany (mappedBy = "ville")
	@JsonIgnore
	private List<Activite> listeA;
	
	public Ville(String nom, Pays pays) {
		super();
		this.nom = nom;
		this.pays = pays;
	}
	
	
	
	
}
