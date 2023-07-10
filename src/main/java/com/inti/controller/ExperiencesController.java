package com.inti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Experiences;
import com.inti.repository.IExperiencesRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ExperiencesController {
	
	@Autowired
	IExperiencesRepository ier;
	
	
	@GetMapping("listeExperiences")
	public List<Experiences> listeExperiences() {
		return ier.findAll();
	}
	
	
	@GetMapping("getExperiencesById/{idExperience}")
	public Experiences getExperiencesById(@PathVariable("idExperience") int idExperience) {
		return ier.getReferenceById(idExperience);
	}
	
	
	@PostMapping("saveExperiences")
	public Experiences saveExperiences(@RequestBody Experiences Experiences) {
		
		
		
		return ier.save(Experiences);
	}
	
	@PutMapping("updateExperiences")
	public boolean updateExperiences(@RequestBody Experiences Experiences) {
		if(ier.getReferenceById(Experiences.getIdExperience()) != null) {
			ier.save(Experiences);
			return true;
		}
		
		return false;
	}
	
	@DeleteMapping("deleteExperiences/{idExperience}")
	public boolean deleteExperiences(@PathVariable("idExperience") int idExperience) {
		
		try {
			ier.findById(idExperience).get();
			ier.delete(ier.getReferenceById(idExperience));
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	

}
