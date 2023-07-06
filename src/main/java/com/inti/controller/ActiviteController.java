package com.inti.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Activite;
import com.inti.repository.ActiviteRepository;

@RestController
@RequestMapping("/activite")
@CrossOrigin(origins = "http://localhost:4200")
public class ActiviteController {
	@Autowired
	ActiviteRepository activiteRepository;
	
	@PostMapping("/add")
  public Boolean createActivite(@RequestBody Activite activite) {
		System.out.println(activite);
		try {
      activiteRepository.save(activite);
      return true;
	  } catch (Exception e) {
	      return false;
	  }
  }
	
	@GetMapping("/get")
	public List<Activite> getActivites() {
		return activiteRepository.findAll();
	}
	
	@GetMapping("/get/{id}")
	public Optional<Activite> getActivite(@PathVariable("id") String id) {
		return activiteRepository.findById(Integer.parseInt(id));
	}
	
	@PutMapping("/update/{id}")
  public Boolean updateActivite(@PathVariable("id") String id, @RequestBody Activite activite) {
    Optional<Activite> activiteOptional = activiteRepository.findById(Integer.parseInt(id));

    if (activiteOptional.isPresent()) {
    	activiteRepository.save(activite);
      return true;
    }
    return false;
  }
	
	@DeleteMapping("/delete/{id}")
  public Boolean deleteActivite(@PathVariable("id") String id) {
    Optional<Activite> activiteOptional = activiteRepository.findById(Integer.parseInt(id));

    if (activiteOptional.isPresent()) {
        activiteRepository.delete(activiteOptional.get());
        return true;
    }
    return false;
  }
}
