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
import com.inti.model.Ville;
import com.inti.repository.IPaysRepository;
import com.inti.repository.IVilleRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PaysController {

/*
 * Appel des répository pour pouvoir communiquer avec la base de donées
 */
	@Autowired
	IPaysRepository ipr;
	
	@Autowired
	IVilleRepository ivr;
	

/*
 * Recupération des données inséré dans le formulaire grace à requestBody
 * Implémentation des données récupérées grace à ipr.save
 */
	@PostMapping("creerPays")
	public Pays savePays(@RequestBody Pays p) {
		return ipr.save(p);
	}
	
/*
 * Recupération de l'ensemble des pays retournée dans une liste
 */
	@GetMapping("listePays")
	public List<Pays> listePays() {
		List<Pays>l=ipr.findAll();
		System.out.println(l);
		return ipr.findAll();
	}
	
/*
 * Suppresssion d'un pays a partir de son id qui a été transmit dans l'url
 * Récupération de l'id dans l'url grace à Pathvariable
 * Supression dans la base de donée avec ipr.delete
 */	
	@DeleteMapping("deletePays/{id}")
	public boolean deleteUtilisateur(@PathVariable("id") int id)
	{
		try {
			Pays p = ipr.findById(id).get();
			ipr.delete(p);
			return true;
		} catch (Exception e) {
		return false;
		}
	}
	
/*
 * Modification d'un pays a partir d'un formulaire
 * Récupération des données inséré dans le formulaire grace à RequestBody
 * On verifie dans un premier temps que le pays existe dans la base
 * Puis on modification dans la base de donée avec ipr.save
 */		
	@PutMapping("modifierPays")
	public boolean modifierPays(@RequestBody Pays pays) {
		if(ipr.getReferenceById(pays.getId()) != null)
		{
			ipr.save(pays);
			return true;
		}
		return false;
	}

/*
 * Récupération d'un pays dans la base de données grace à son id
 * L'id du pays récupérer est transmit par la pathVariable
 * La récupération dans la base de donnée est faite par la fonction ipr.getReferenceById
 */
	@GetMapping("getPays/{id}")
	public Pays getPays(@PathVariable("id") int id) {
		return ipr.getReferenceById(id);
	}

}
