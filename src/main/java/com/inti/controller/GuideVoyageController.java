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

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class GuideVoyageController {
	
	@Autowired
	IGuideVoyageRepository igv;
	@Autowired
	IUtilisateurRepository iur;
	@Autowired
	ActiviteRepository iar;
	@Autowired
	RestaurantRepository restaurantRepository;
	@Autowired
	LieuRepository lieuRepository;
	
	
	@GetMapping("listeGuideVoyage/{nom}")
	public List<GuideVoyage> listeGuideVoyage(@PathVariable("nom") String nom)
	{

		List<GuideVoyage> listeGV = igv.findAll();
		List<Utilisateur> listeU;
		for (GuideVoyage guideVoyage : listeGV) {
			listeU=iur.getListeUtilisateurByGVId(guideVoyage.getIdGuide());
			guideVoyage.setListeU(listeU);
		}
		
		

		if(nom.contentEquals("undefined")) {
			return listeGV;
		}
		else {
			return igv.getGuideByLocalisation(nom);
		}

	}
	
	@GetMapping("doesGuideExist/{nom}")
	public Boolean doesGuideExist(@PathVariable("nom") String nom) {
		if(!igv.doesGuideExist(nom).isEmpty() || nom.contentEquals("undefined")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@PostMapping("saveGuideVoyage")
	public GuideVoyage saveGuideVoyage(@RequestBody GuideVoyage GuideVoyage)
	{

		GuideVoyage gvSaved = igv.save(GuideVoyage);
		

		//List<Utilisateur> listeU = GuideVoyage.getListeU();
		//System.out.println(listeU);
//	    for (Utilisateur utilisateur : listeU) {
//	        utilisateur.getListeG().add(gvSaved);
//	        iur.save(utilisateur);
//	    }
	    
	    

		return gvSaved;
	}
	
	@PutMapping("modifierGuideVoyage")
	public boolean modifierGuideVoyage(@RequestBody GuideVoyage g)
	{
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkkk"+ g.getNom() + "|" +g.getActivites().size());
		for (Activite activite : g.getActivites()) 
		{
			System.out.println("uuuuuu" + activite.getNom() + activite.getCommentaire() + activite.getId() + activite.getDepense());
		}
		if(igv.getReferenceById(g.getIdGuide())!=null)
		{
		igv.save(g);
		return true;
		}
		return false;
	}
	
	@DeleteMapping("deleteGuideVoyage/{id}")
	public boolean deleteGuideVoyage(@PathVariable("id") int id)
	{
		try 
		{
			igv.findById(id).get();
			GuideVoyage GuideVoyage1 = igv.getReferenceById(id);
			igv.delete(GuideVoyage1);
			
			return true;
			
		}catch(Exception e1)
		
		{
			return false;
		}
		
		
	}
	@GetMapping("getGuideVoyageById/{idGuide}")
	public GuideVoyage getGuideVoyageById(@PathVariable("idGuide") int idGuide) {
		return igv.getReferenceById(idGuide);
	}
	
	@GetMapping("approuverGuide/{idGuide}")
	public boolean approuverGuide(@PathVariable("idGuide") int idGuide) {
		try {
			GuideVoyage guideVoyage = igv.getReferenceById(idGuide);
			guideVoyage.setEstApprouve(true);
			igv.save(guideVoyage);
			return true;
		} catch (Exception e) {
		}
		return false;
	}	
}
