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

/**
 * Controller de la classe Lieu
 *
 */
@RestController
@RequestMapping("/lieu")
@CrossOrigin(origins = "http://localhost:4200")
public class LieuController {
	/**
	 * Injection du repository de la classe Lieu
	 */
	@Autowired
	LieuRepository lieuRepository;
	
	/**
	 * Méthode pour créer un lieu.
	 * @param lieu : le lieu à créer.
	 * @return true si le lieu a été créé avec succès, false sinon.
	 */
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
	
	/**
	 * Méthode pour récupérer une liste des lieux.
	 * @return la liste des lieux.
	 */
	@GetMapping("/get")
	public List<Lieu> getLieux() {
		return lieuRepository.findAll();
	}
	
	/**
	 * Méthode pour récupérer un lieu.
	 * @param id : l'identifiant du lieu à récupérer.
	 * @return le lieu d'identifiant donné.
	 */
	@GetMapping("/get/{id}")
	public Optional<Lieu> getLieu(@PathVariable("id") String id) {
		return lieuRepository.findById(Integer.parseInt(id));
	}
	
	/**
	 * Méthode pour modifier un lieu.
	 * @param id : l'identifiant du lieu à modifier.
	 * @param lieu : le lieu à modifier
	 * @return true si le lieu a été modifié avec succès, false sinon.
	 */
	@PutMapping("/update/{id}")
  public Boolean updateLieu(@PathVariable("id") String id, @RequestBody Lieu lieu) {
    Optional<Lieu> lieuOptional = lieuRepository.findById(Integer.parseInt(id));

    if (lieuOptional.isPresent()) {
    	lieuRepository.save(lieu);
      return true;
    }
    return false;
  }
	
	/**
	 * Méthode pour supprimer un lieu.
	 * @param id : l'identifiant du lieu à supprimer
	 * @return true si le lieu a été supprimé avec succès, false sinon.
	 */
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
