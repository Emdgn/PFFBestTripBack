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

	@Autowired
	IVilleRepository ivr;
	
	@Autowired
	IPaysRepository ipr;
	
	@PostMapping("creerVille")
	public Ville ajoutVille(@RequestBody Ville v /* , @RequestParam("pays") int pays*/) {
//		Pays p=ipr.getReferenceById(pays);
//		Ville v= new  Ville(nom, p);
		return ivr.save(v);
	}
	
	@GetMapping("listeVille")
	public List<Ville> listeVille () {
//		System.out.println(ivr.findAll());
		return ivr.findAll();
	}
	
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
	
	@PutMapping("modifierVille")
	public boolean modifierVille(@RequestBody Ville ville) {
		if(ivr.getReferenceById(ville.getId()) != null)
		{
			ivr.save(ville);
			return true;
		}
		return false;
	}
	
	
	@GetMapping("getVille/{id}")
	public Ville getVille(@PathVariable("id") int id)
	{
		return ivr.getReferenceById(id);
	}

}
