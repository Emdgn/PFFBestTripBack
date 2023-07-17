package com.inti.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Activite;
import com.inti.model.Experiences;
import com.inti.model.Lieu;
import com.inti.model.Restaurant;
import com.inti.repository.ActiviteRepository;
import com.inti.repository.IExperiencesRepository;
import com.inti.repository.IUtilisateurRepository;
import com.inti.repository.LieuRepository;
import com.inti.repository.RestaurantRepository;

/**
 * Controller de la classe Experiences
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ExperiencesController {
	/**
	 * Injection du repository de la classe Experiences
	 */
	@Autowired
	IExperiencesRepository ier;
	/**
	 * Injection du repository de la classe Activite
	 */
	@Autowired
	ActiviteRepository activiteRepository;
	/**
	 * Injection du repository de la classe Utilisateur
	 */
	@Autowired
	IUtilisateurRepository iur;
	/**
	 * Injection du repository de la classe Restaurant
	 */
	@Autowired
	RestaurantRepository restaurantRepository;
	/**
	 * Injection du repository de la classe Lieu
	 */
	@Autowired
	LieuRepository lieuRepository;
	
	
	/**
	 * Méthode pour récupérer une liste des activités d'un type donné.
	 * @param type : le type de l'expérience.
	 * @return la liste des activités.
	 */
	@GetMapping("listeExperiences/{type}")
	public List<Experiences> listeExperiences(@PathVariable("type") String type) {
		
		if(type.contentEquals("undefined")) {
			return ier.findAll();
		}
		else {
			return ier.getExperiencesByType(type);
		}
		
	}
	
	/**
	 * Méthode pour vérifier s'il existe au moins une expérience d'un type donné.
	 * @param type : le type de l'expérience.
	 * @return true s'il existe au moins une expérience d'un type donné, false sinon.
	 */
	@GetMapping("doesExperienceExist/{type}")
	public Boolean doesExperienceExist(@PathVariable("type") String type) {
		if(ier.doesExperienceExist(type).isEmpty() || type.contentEquals("undefined")) {
			return true;
		}
		else {
			return false;
		}
	}
	

	/**
	 * Méthode pour récupérer une expérience.
	 * @param idExperience : l'identifiant de l'expérience à récupérer.
	 * @return l'expérience d'identifiant donné.
	 */
	@GetMapping("getExperiencesById/{idExperience}")
	public Experiences getExperiencesById(@PathVariable("idExperience") int idExperience) {
		System.out.println(ier.getReferenceById(idExperience));
		System.out.println(ier.getReferenceById(idExperience).getActivites());
		
		return ier.getReferenceById(idExperience);
	}
	
	/**
	 * Méthode pour créer une expérience.
	 * @param experiences : l'expérience à créer.
	 * @param username : username de l'utilisateur qui créé l'expérience.
	 * @return l'expérience qui vient d'être créée.
	 */
	@PostMapping("saveExperiences")
	public Experiences saveExperiences(@RequestBody Experiences experiences, @RequestParam("username") String username) {
		Experiences savedExperiences = ier.save(experiences);
		
		System.out.println("Username : " + username);
		Optional<Integer> optionalId = iur.getIdByUsername(username);
		
		ier.insertIdUtilisateur(optionalId.orElse(-1), savedExperiences.getIdExperience());

    List<Activite> activites = experiences.getActivites();
    for (Activite activite : activites) {
        activite.setExperience(savedExperiences);

        activiteRepository.save(activite);
    }

    return savedExperiences;
	}
	
	/**
	 * Méthode qui permet de modifier une expérience.
	 * @param experiences : l'expérience à modifier.
	 * @param username : username de l'utilisateur de l'expérience.
	 * @return true si l'expérience a été modifiée avec succès, false sinon.
	 */
	@PutMapping("updateExperiences")
	public boolean updateExperiences(@RequestBody Experiences experiences, @RequestParam("username") String username) {

		System.out.println(username);
		if(ier.getReferenceById(experiences.getIdExperience()) != null) {
			// ---------- Update Activites ----------
			
			List<Restaurant> newRestaurants = new ArrayList<>();
			List<Lieu> newLieux = new ArrayList<>();
			for (Activite activite : experiences.getActivites()) {
				if (activite instanceof Restaurant) {
					newRestaurants.add((Restaurant) activite);
				} else {
					newLieux.add((Lieu) activite);
				}
			}
			
			// ---------- Update Restaurants ----------
			List<Integer> idsOldRestaurants = restaurantRepository.getByIdExperience(experiences.getIdExperience());
			List<Integer> idsNewRestaurants = new ArrayList<>();
			Activite a;
			int idRestaurant;
			for (Restaurant restaurant : newRestaurants) {
				idRestaurant = restaurant.getId();
				
				// add/update restaurants
				a = (Activite) restaurant;
				a.setExperience(experiences);
				activiteRepository.save(a);
				if (idRestaurant != 0) {
					idsNewRestaurants.add(idRestaurant);
				}
			}

			List<Integer> idsRestaurantsToRemove = new ArrayList<>(idsOldRestaurants);
			idsRestaurantsToRemove.removeAll(idsNewRestaurants);
			List<Integer> idsRestaurantsToUpdate = new ArrayList<>(idsOldRestaurants);
			idsRestaurantsToUpdate.retainAll(idsNewRestaurants);
			
			for (int id : idsRestaurantsToRemove) {
				// delete restaurants
				activiteRepository.delete(activiteRepository.getReferenceById(id));
			}
			
			// ---------- Update Lieux ---------- 
			List<Integer> idsOldLieux = lieuRepository.getByIdExperience(experiences.getIdExperience());
			List<Integer> idsNewLieux = new ArrayList<>();
			int idLieu;
			for (Lieu lieu : newLieux) {
				idLieu = lieu.getId();

				// add/update lieux
				a = (Activite) lieu;
				a.setExperience(experiences);
				activiteRepository.save(a);
				if (idLieu != 0) {
					idsNewLieux.add(idLieu);
				}
			}

			List<Integer> idsLieuxToRemove = new ArrayList<>(idsOldLieux);
			idsLieuxToRemove.removeAll(idsNewLieux);
			List<Integer> idsLieuxToUpdate = new ArrayList<>(idsOldLieux);
			idsLieuxToUpdate.retainAll(idsNewLieux);
			
			for (int id : idsLieuxToRemove) {
				// delete lieux
				activiteRepository.delete(activiteRepository.getReferenceById(id));
			}
			
			// ---------- Update Experience ----------
			ier.save(experiences);
			
			// ---------- Update Utilisateur ----------
			// on cherche l'id de l'utilisateur en fonction de son username
			// check si le compte est créé ou pas
			Optional<Integer> optionalId = iur.getIdByUsername(username);
			int idUtilisateur = optionalId.orElse(-1);
			if (idUtilisateur != -1) {
				ier.insertIdUtilisateur(idUtilisateur, ier.save(experiences).getIdExperience());
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Méthode pour supprimer une expérience.
	 * @param idExperience : l'identifiant de l'expérience à supprimer.
	 * @return true si l'expérience a été supprimée avec succès, false sinon.
	 */
	@DeleteMapping("deleteExperiences/{idExperience}")
	public boolean deleteExperiences(@PathVariable("idExperience") int idExperience) {
	    try {
	        Experiences experience = ier.findById(idExperience).get();
	        
	        for (Activite activite : experience.getActivites()) {
	            activiteRepository.delete(activite);
	        }

	        ier.delete(experience);
	        
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	/**
	 * Méthode pour récupérer l'username de l'utilisateur d'une expérience donnée.
	 * @param idExperience : l'identifiant de l'expérience.
	 * @return l'username de l'utilisateur d'une expérience donnée.
	 */
	@GetMapping("getUsernameById/{idExperience}")
	public String getUsernameById(@PathVariable("idExperience") int idExperience)
	{
		Optional<Integer> optionalId = ier.getIdUtilisateurByIdExp(idExperience);
		int idUtilisateur = optionalId.orElse(-1);
		return idUtilisateur != -1 ? iur.getUsernameById(idUtilisateur) : "";
	}
	
	@GetMapping("approuverExperiences/{idExperience}")
	public boolean approuverExperiences(@PathVariable("idExperience") int idExperience) {
		try {
			Experiences experience = ier.getReferenceById(idExperience);
			experience.setEstApprouvee(true);
			ier.save(experience);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
