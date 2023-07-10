package com.inti.model;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Table
@Entity
@Data
@PrimaryKeyJoinColumn(name = "idActivite")
@EqualsAndHashCode (callSuper = false)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@ToString(callSuper = true)
public class Lieu extends Activite {
	private String type;

	public Lieu(int id, String nom, String commentaire, List<String> photos, String videos, double depense,
			String type) {
		super(id, nom, commentaire, photos, videos, depense);
		this.type = type;
	}
}
