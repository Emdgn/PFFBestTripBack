package com.inti.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inti.model.Activite;
import com.inti.model.GuideVoyage;
import com.inti.repository.ActiviteRepository;
import com.inti.repository.IGuideVoyageRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class GuideVoyageController {
	
	@Autowired
	IGuideVoyageRepository igv;
	
	@Autowired
	ActiviteRepository iar;
	
	@GetMapping("listeGuideVoyage")
	public List<GuideVoyage> listeGuideVoyage()
	{
		System.out.println("test:" + igv.findAll());
		return igv.findAll();
	}
	
	@PostMapping("saveGuideVoyage")
	public GuideVoyage saveGuideVoyage(@RequestBody GuideVoyage GuideVoyage)
	{
		System.out.println("guide" + GuideVoyage);
//		for ( Activite activite : GuideVoyage.getActivites()) {
//			
//		iar.save(activite);
			
//		}
//		GuideVoyage gv = new GuideVoyage(GuideVoyage.getNom(), GuideVoyage.getDateCreation(), GuideVoyage.getDescription());
		return igv.save(GuideVoyage);
	}
	
	@PutMapping("modifierGuideVoyage")
	public boolean modifierGuideVoyage(@RequestBody GuideVoyage g)
	{
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
}
