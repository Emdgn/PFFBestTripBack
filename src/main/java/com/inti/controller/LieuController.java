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

import com.inti.model.Lieu;
import com.inti.repository.LieuRepository;

@RestController
@RequestMapping("/lieu")
@CrossOrigin(origins = "http://localhost:4200")
public class LieuController {
	@Autowired
	LieuRepository lieuRepository;
	
	@PostMapping("/add")
  public Boolean createLieu(@RequestBody Lieu lieu) {
		System.out.println(lieu);
		try {
      lieuRepository.save(lieu);
      return true;
	  } catch (Exception e) {
	      return false;
	  }
  }
	
	@GetMapping("/get")
	public List<Lieu> getLieux() {
		return lieuRepository.findAll();
	}
	
	@GetMapping("/get/{id}")
	public Optional<Lieu> getLieu(@PathVariable("id") String id) {
		return lieuRepository.findById(Integer.parseInt(id));
	}
	
	@PutMapping("/update/{id}")
  public Boolean updateLieu(@PathVariable("id") String id, @RequestBody Lieu lieu) {
    Optional<Lieu> lieuOptional = lieuRepository.findById(Integer.parseInt(id));

    if (lieuOptional.isPresent()) {
    	lieuRepository.save(lieu);
      return true;
    }
    return false;
  }
	
	@DeleteMapping("/delete/{id}")
  public Boolean deleteLieu(@PathVariable("id") String id) {
    Optional<Lieu> lieuOptional = lieuRepository.findById(Integer.parseInt(id));

    if (lieuOptional.isPresent()) {
        lieuRepository.delete(lieuOptional.get());
        return true;
    }
    return false;
  }
}
