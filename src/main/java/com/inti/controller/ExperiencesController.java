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

import com.inti.model.Activite;
import com.inti.model.Experiences;
import com.inti.repository.ActiviteRepository;
import com.inti.repository.IExperiencesRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ExperiencesController {
	
	@Autowired
	IExperiencesRepository ier;
	@Autowired
	ActiviteRepository activiteRepository;
	
	
	@GetMapping("listeExperiences")
	public List<Experiences> listeExperiences() {
		return ier.findAll();
	}
	
	
	@GetMapping("getExperiencesById/{idExperience}")
	public Experiences getExperiencesById(@PathVariable("idExperience") int idExperience) {
		System.out.println(ier.getReferenceById(idExperience));
		System.out.println(ier.getReferenceById(idExperience).getActivites());
		return ier.getReferenceById(idExperience);
	}
	
	
	@PostMapping("saveExperiences")
	public Experiences saveExperiences(@RequestBody Experiences experiences) {
		Experiences savedExperiences = ier.save(experiences);

    List<Activite> activites = experiences.getActivites();
    for (Activite activite : activites) {
        activite.setExperience(savedExperiences);

        activiteRepository.save(activite);
    }

    return savedExperiences;
	}
	
	@PutMapping("updateExperiences")
	public boolean updateExperiences(@RequestBody Experiences experiences) {
		if(ier.getReferenceById(experiences.getIdExperience()) != null) {
			ier.save(experiences);
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
