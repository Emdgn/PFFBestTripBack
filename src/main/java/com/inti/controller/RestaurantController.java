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

import com.inti.model.Restaurant;
import com.inti.repository.RestaurantRepository;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "http://localhost:4200")
public class RestaurantController {
	
/*
 * Appel la répository pour pouvoir communiquer avec la base de donées
 */
	@Autowired
	RestaurantRepository restaurantRepository;

/*
 * Création d'une novelle entité restaurant dans la base de donée
 * Recupération des données inséré dans le formulaire grace a requestBody
 * Implémentation des données récupérées grace a ipr.save
 */
	@PostMapping("/add")
  public Boolean createRestaurant(@RequestBody Restaurant restaurant) {
		System.out.println(restaurant);
	try {
		restaurantRepository.save(restaurant);
      return true;
	  } catch (Exception e) {
	     return false;
	  }
  }

/*
 * Recupération de l'ensemble des restaurant retournée dans une liste
 */
	@GetMapping("/get")
	public List<Restaurant> getRestaurants() {
		return restaurantRepository.findAll();
	}

/*
 * Récupération d'un restaurant dans la base de données grace à son id
 * L'id est récupérer est transmit par la pathVariable
 * La récupération dans la base de donnée est faite par la fonction ipr.getReferenceById
 */
	@GetMapping("/get/{id}")
	public Optional<Restaurant> getRestaurant(@PathVariable("id") String id) {
		return restaurantRepository.findById(Integer.parseInt(id));
	}
	
/*
 * Modification d'un restaurant à partir d'un formulaire
 * Récupération des données inséré dans le formulaire grace à RequestBody
 * On verifie dans un premier temps que le restaurant existe dans la base
 * Puis on modification dans la base de donée avec ipr.save
 */			
	@PutMapping("/update/{id}")
  public Boolean updateRestaurant(@PathVariable("id") String id, @RequestBody Restaurant restaurant) {
    Optional<Restaurant> restaurantOptional = restaurantRepository.findById(Integer.parseInt(id));

    if (restaurantOptional.isPresent()) {
    	restaurantRepository.save(restaurant);
      return true;
    }
    return false;
  }

/*
 * Suppresssion d'un restaurant à partir de son id qui a été transmit dans l'url
 * Récupération de l'id dans l'url grace à Pathvariable
 * Supression dans la base de donée avec ipr.delete
 */	
	@DeleteMapping("/delete/{id}")
  public Boolean deleteRestaurant(@PathVariable("id") String id) {
    Optional<Restaurant> restaurantOptional = restaurantRepository.findById(Integer.parseInt(id));

    if (restaurantOptional.isPresent()) {
        restaurantRepository.delete(restaurantOptional.get());
        return true;
    }
    return false;
  }
}
