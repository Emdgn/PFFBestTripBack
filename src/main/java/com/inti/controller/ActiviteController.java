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

/**
 * Controller de la classe Activite
 *
 */
@RestController
@RequestMapping("/activite")
@CrossOrigin(origins = "http://localhost:4200")
public class ActiviteController {
	/**
	 * Injection du repository de la classe Activite
	 */
	@Autowired
	ActiviteRepository activiteRepository;
	
	/**
	 * Méthode pour créer une activité.
	 * @param activite : l'activité à créer.
	 * @return true si l'activité a été créée avec succès, false sinon.
	 */
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
	
	/**
	 * Méthode pour récupérer une liste des activités.
	 * @return la liste des activités.
	 */
	@GetMapping("/get")
	public List<Activite> getActivites() {
		return activiteRepository.findAll();
	}
	
	/**
	 * Méthode pour récupérer une activité.
	 * @param id : l'identifiant de l'activité à récupérer.
	 * @return l'activité d'identifiant donné.
	 */
	@GetMapping("/get/{id}")
	public Optional<Activite> getActivite(@PathVariable("id") String id) {
		return activiteRepository.findById(Integer.parseInt(id));
	}
	
	/**
	 * Méthode pour modifier une activité.
	 * @param id : l'identifiant de l'activité à modifier.
	 * @param activite : l'activité à modifier.
	 * @return true si l'activité a été modifiée avec succès, false sinon.
	 */
	@PutMapping("/update/{id}")
  public Boolean updateActivite(@PathVariable("id") String id, @RequestBody Activite activite) {
    Optional<Activite> activiteOptional = activiteRepository.findById(Integer.parseInt(id));

    if (activiteOptional.isPresent()) {
    	activiteRepository.save(activite);
      return true;
    }
    return false;
  }
	
	/**
	 * Méthode pour supprimer une activité.
	 * @param id : l'identifiant de l'activité à supprimer.
	 * @return true si l'activité à été supprimée avec succès, false sinon.
	 */
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
