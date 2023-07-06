package com.inti.model;

import jakarta.persistence.PrimaryKeyJoinColumn;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table
@Entity
@Data
@PrimaryKeyJoinColumn(name = "idActivite")
@EqualsAndHashCode (callSuper = false)
public class Restaurant extends Activite {
	private String type;
	private String adresse;
}
