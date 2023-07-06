package com.inti.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table
@Entity
@Data
@PrimaryKeyJoinColumn(name = "idActivite")
@EqualsAndHashCode (callSuper = false)
public class Lieu extends Activite {
	private String type;
}
