package com.inti.controller;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inti.model.Activite;
import com.inti.model.Experiences;
import com.inti.model.GuideVoyage;
import com.inti.model.Utilisateur;
import com.inti.repository.ActiviteRepository;
import com.inti.repository.IGuideVoyageRepository;
import com.inti.repository.IUtilisateurRepository;
import com.inti.repository.LieuRepository;
import com.inti.repository.RestaurantRepository;

@RestController // Indique que c'est une classe de contrôleur REST
@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes CORS depuis l'URL spécifiée
public class GuideVoyageController {
	
	@Autowired // Injection de dépendance pour IGuideVoyageRepository
	IGuideVoyageRepository igv;
	@Autowired // Injection de dépendance pour IUtilisateurRepository
	IUtilisateurRepository iur;
	@Autowired // Injection de dépendance pour ActiviteRepository
	ActiviteRepository iar;
	@Autowired // Injection de dépendance pour RestaurantRepository
	RestaurantRepository restaurantRepository;
	@Autowired // Injection de dépendance pour LieuRepository
	LieuRepository lieuRepository;
	
	
	@GetMapping("listeGuideVoyage/{nom}")
	public List<GuideVoyage> listeGuideVoyage(@PathVariable("nom") String nom) {
		// Récupérer la liste de tous les GuideVoyage
		List<GuideVoyage> listeGV = igv.findAll();
		List<Utilisateur> listeU;
		for (GuideVoyage guideVoyage : listeGV) {
			// Récupérer la liste des Utilisateur associés à chaque GuideVoyage
			listeU = iur.getListeUtilisateurByGVId(guideVoyage.getIdGuide());
			guideVoyage.setListeU(listeU);
		}
		
		if (nom.contentEquals("undefined")) {
			return listeGV;
		} else {
			return igv.getGuideByLocalisation(nom);
		}
	}
	
	@GetMapping("doesGuideExist/{nom}")
	public Boolean doesGuideExist(@PathVariable("nom") String nom) {
		if (!igv.doesGuideExist(nom).isEmpty() || nom.contentEquals("undefined")) {
			return true;
		} else {
			return false;
		}
	}
	
	@PostMapping("saveGuideVoyage")
	public GuideVoyage saveGuideVoyage(@RequestBody GuideVoyage GuideVoyage) {
		// Enregistrer le GuideVoyage
		GuideVoyage gvSaved = igv.save(GuideVoyage);    
		return gvSaved;
	}
	
	@PutMapping("modifierGuideVoyage")
	public boolean modifierGuideVoyage(@RequestBody GuideVoyage g) {
		// Afficher les informations sur les activités
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkkk" + g.getNom() + "|" + g.getActivites().size());// ajout de trace
		for (Activite activite : g.getActivites()) {
			System.out.println("uuuuuu" + activite.getNom() + activite.getCommentaire() + activite.getId() + activite.getDepense());//ajout de trace
		}
		if (igv.getReferenceById(g.getIdGuide()) != null) {
			// Mettre à jour les Utilisateur associés au GuideVoyage
			List<Utilisateur> listeU = g.getListeU();
			System.out.println(listeU);
			for (Utilisateur utilisateur : listeU) {
				utilisateur.getListeG().add(g);
				iur.save(utilisateur);
			}
			igv.save(g);
			return true;
		}
		return false;
	}
	
	@DeleteMapping("deleteGuideVoyage/{id}")// supprimer guide de voyage par son id
	public boolean deleteGuideVoyage(@PathVariable("id") int id) {
		try {
			igv.findById(id).get();
			GuideVoyage GuideVoyage1 = igv.getReferenceById(id);
			igv.delete(GuideVoyage1);
			
			return true;
			
		} catch (Exception e1) {
			return false;
		}
	}
	
	@GetMapping("getGuideVoyageById/{idGuide}")
	public GuideVoyage getGuideVoyageById(@PathVariable("idGuide") int idGuide) {
		// Récupérer le GuideVoyage en fonction de l'ID
		return igv.getReferenceById(idGuide);
	}

	@GetMapping("approuverGuide/{idGuide}")
	public boolean approuverGuide(@PathVariable("idGuide") int idGuide) {
		try {
			// Récupérer le GuideVoyage en fonction de l'ID
			GuideVoyage guideVoyage = igv.getReferenceById(idGuide);
			// Approuver le GuideVoyage en le marquant comme approuvé
			guideVoyage.setEstApprouve(true);
			igv.save(guideVoyage);
			return true;
		} catch (Exception e) {
			// Gérer les exceptions potentielles
		}
		return false;
	}

	@PutMapping("setNoteGuide")
	public void setNoteGuide(@RequestParam("idGuide") int idGuide, @RequestParam("notation") int notation) {
		// Insérer le nombre de notes et la somme des notes pour le GuideVoyage
		igv.insertNbrSommeNoteGuide(idGuide, notation);
		// Calculer la note moyenne pour le GuideVoyage
		igv.insertNoteGuide(idGuide);
	}
}