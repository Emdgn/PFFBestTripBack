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

	@Autowired
	IPaysRepository ipr;
	
	@Autowired
	IVilleRepository ivr;
	
	
	@PostMapping("creerPays")
	public Pays savePays(@RequestBody Pays p) {
		return ipr.save(p);
	}
	
	@GetMapping("listePays")
	public List<Pays> listePays() {
		List<Pays>l=ipr.findAll();
		System.out.println(l);
		return ipr.findAll();
	}
	
	
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
	
	@PutMapping("modifierPays")
	public boolean modifierPays(@RequestBody Pays pays) {
		if(ipr.getReferenceById(pays.getId()) != null)
		{
			ipr.save(pays);
			return true;
		}
		return false;
	}
	
	@GetMapping("getPays/{id}")
	public Pays getPays(@PathVariable("id") int id) {
		return ipr.getReferenceById(id);
	}

}
