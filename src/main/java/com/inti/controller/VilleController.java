package com.inti.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Pays;
import com.inti.model.Ville;
import com.inti.repository.IPaysRepository;
import com.inti.repository.IVilleRepository;

@RestController
public class VilleController {

	@Autowired
	IVilleRepository ivr;
	
	@Autowired
	IPaysRepository ipr;
	
	
	@GetMapping("CreerVille")
	public String ajoutVille(Model m) {
		try {
			m.addAttribute("listeP", ipr.findAll());
			return "creerVille";
		} catch (Exception e) {
			e.printStackTrace();
			return "creerVille";
		}
	}
	
	@PostMapping("creerVille")
	public String ajoutVille(@RequestParam("nom") String nom,@RequestParam("pays") int pays) {
		Pays p=ipr.getReferenceById(pays);
		Ville v= new  Ville(nom, p);
		ivr.save(v);
		return "redirect:/listeVille";
	}
	
	@GetMapping("listeVille")
	public String Ville(Model m) {
		m.addAttribute("listeP", ivr.findAll());
		return "listeVille";
	}
	
	@GetMapping("deleteVille/{id}")
		public String deleteVille(@PathVariable("id")int id) {
		Ville p=ivr.getReferenceById(id);
		ivr.delete(p);
		return "redirect:/listeVille";
		}
	
	@PutMapping("modifierVille/{id}")
		public String modifierVille(@PathVariable("id")int id,Model m) {
		Ville p=ivr.getReferenceById(id);
			m.addAttribute("p", p);
		return "modifierVille";
	}
	
	@PostMapping("modifierVille/{id}")
		public String modifierVille(@ModelAttribute("ville")Ville p,@PathVariable("id")int id) {
		ivr.save(p);
		return "redirect:/listeVille";
	}

}
