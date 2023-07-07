package com.inti.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
public class Lieu extends Activite {
	private String type;
}
