package com.inti.model;

import jakarta.persistence.PrimaryKeyJoinColumn;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table
@Entity
@Data
@PrimaryKeyJoinColumn(name = "idActivite")
@EqualsAndHashCode (callSuper = false)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@ToString(callSuper = true)
@NoArgsConstructor
public class Restaurant extends Activite {
	private String type;
	private String adresse;
	
	public Restaurant(int id, String nom, String commentaire, List<String> photos, String videos, double depense,
			String type, String adresse) {
		super(id, nom, commentaire, photos, videos, depense);
		this.type = type;
		this.adresse = adresse;
	}
}
