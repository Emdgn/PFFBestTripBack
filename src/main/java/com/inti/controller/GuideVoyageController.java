package com.inti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.GuideVoyage;
import com.inti.repository.IGuideVoyageRepository;

@RestController
public class GuideVoyageController {
	
	@Autowired
	IGuideVoyageRepository igv;

	@GetMapping("hello")
	private String hello()
	{
		return "Hello World !";
	}
	
	@GetMapping("listeGuideVoyage")
	public List<GuideVoyage> listeGuideVoyage()
	{
		return igv.findAll();
	}
	
	@PostMapping("saveGuideVoyage")
	public GuideVoyage saveGuideVoyage(@RequestBody GuideVoyage GuideVoyage)
	{
		return igv.save(GuideVoyage);
	}
	
	@PutMapping("modifierGuideVoyage")
	public boolean modifigvGuideVoyage(@RequestBody GuideVoyage g)
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
}
