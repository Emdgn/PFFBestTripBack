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
import com.inti.repository.IPaysRepository;

@RestController
public class PaysController {

	@Autowired
	IPaysRepository ipr;

	
	
	@GetMapping("CreerPays")
	public String ajoutPays(Model m) {
		return "creerPays";
		
	}
	
	@PostMapping("creerPays")
	public String ajoutPays(@RequestParam("nom") String nom) {
		Pays p= new Pays(nom);
		ipr.save(p);
		return "redirect:/listePays";
	}
	
	@GetMapping("listePays")
	public String Pays(Model m) {
		m.addAttribute("listeP", ipr.findAll());
		return "listePays";
	}
	
	@GetMapping("deletePays/{id}")
		public String deletePays(@PathVariable("id")int id) {
		Pays p=ipr.getReferenceById(id);
		ipr.delete(p);
		return "redirect:/listePays";
		}
	
	@PutMapping("modifierPays/{id}")
		public String modifierPays(@PathVariable("id")int id,Model m) {
		Pays p=ipr.getReferenceById(id);
			m.addAttribute("p", p);
		return "modifierPays";
	}
	
	@PostMapping("modifierPays/{id}")
		public String modifierPays(@ModelAttribute("pays")Pays p,@PathVariable("id")int id) {
		ipr.save(p);
		return "redirect:/listePays";
	}
}
