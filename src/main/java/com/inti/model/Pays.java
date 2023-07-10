package com.inti.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table
@Data @AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Pays {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String nom;
	
	@OneToMany (mappedBy = "pays")
	@JsonIgnore
	private List<Ville> listeV;

	public Pays(String nom) {
		super();
		this.nom = nom;
	}

	
}
