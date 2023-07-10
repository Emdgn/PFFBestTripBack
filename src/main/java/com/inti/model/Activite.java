package com.inti.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String nom;
    protected String commentaire;
    @Column(length = 10000)
    protected List<String> photos;
    protected String videos;
    protected double depense;

    @ManyToOne
    @JoinColumn(name = "idVille")
    @JsonIgnore
    protected Ville ville;

    @ManyToOne
    @JoinColumn(name = "idExperience")
    @JsonIgnore
    protected Experiences experience;

    @ManyToMany
    @JoinTable(name = "Activite_guideVoyage", joinColumns = @JoinColumn(name = "idActivité"), inverseJoinColumns = @JoinColumn(name = "idGuideVoyage"))
    @JsonIgnore
    protected List<GuideVoyage> listeG;

    public Activite(String nom, String commentaire, List<String> photos, String videos, double depense) {
        this.nom = nom;
        this.commentaire = commentaire;
        this.photos = photos;
        this.videos = videos;
        this.depense = depense;
    }
}
