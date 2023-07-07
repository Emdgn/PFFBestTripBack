package com.inti.model;

import jakarta.persistence.PrimaryKeyJoinColumn;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@PrimaryKeyJoinColumn(name = "idActivite")
@EqualsAndHashCode (callSuper = false)
@AllArgsConstructor @NoArgsConstructor
public class Restaurant extends Activite {
	private String type;
	private String adresse;
}
