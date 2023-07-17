package com.inti.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Pays;
import com.inti.model.Utilisateur;
import com.inti.model.Ville;
import com.inti.repository.IPaysRepository;
import com.inti.repository.IVilleRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VilleController {

	/*
	 * Appel des répository pour pouvoir communiquer avec la base de donées
	 */
	@Autowired
	IVilleRepository ivr;
	
	@Autowired
	IPaysRepository ipr;
	
	
	/*
	 * Recupération des données inséré dans le formulaire grace à requestBody
	 * Implémentation des données récupérées grace à ipr.save
	 */	
	@PostMapping("creerVille")
	public Ville ajoutVille(@RequestBody Ville v /* , @RequestParam("ville") int ville*/) {
		return ivr.save(v);
	}
	
	/*
	 * Recupération de l'ensemble des ville retournée dans une liste
	 */
	@GetMapping("listeVille")
	public List<Ville> listeVille () {
		return ivr.findAll();
	}
	
	/*
	 * Suppresssion d'un ville a partir de son id qui a été transmit dans l'url
	 * Récupération de l'id dans l'url grace à Pathvariable
	 * Supression dans la base de donée avec ipr.delete
	 */
	@DeleteMapping("deleteVille/{id}")
		public boolean deleteVille(@PathVariable("id")int id) {
			try {
				Ville p=ivr.getReferenceById(id);
				ivr.delete(p);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	
	/*
	 * Modification d'un ville a partir d'un formulaire
	 * Récupération des données inséré dans le formulaire grace à RequestBody
	 * On verifie dans un premier temps que le ville existe dans la base
	 * Puis on modification dans la base de donée avec ipr.save
	 */	
	@PutMapping("modifierVille")
	public boolean modifierVille(@RequestBody Ville ville) {
		if(ivr.getReferenceById(ville.getId()) != null)
		{
			ivr.save(ville);
			return true;
		}
		return false;
	}
	
	/*
	 * Récupération d'un ville dans la base de données grace à son id
	 * L'id du ville récupérer est transmit par la pathVariable
	 * La récupération dans la base de donnée est faite par la fonction ipr.getReferenceById
	 */
	@GetMapping("getVille/{id}")
	public Ville getVille(@PathVariable("id") int id)
	{
		return ivr.getReferenceById(id);
	}

	
}
