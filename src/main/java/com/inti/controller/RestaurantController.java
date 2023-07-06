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
	@Autowired
	RestaurantRepository restaurantRepository;
	
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
	
	@GetMapping("/get")
	public List<Restaurant> getRestaurants() {
		return restaurantRepository.findAll();
	}
	
	@GetMapping("/get/{id}")
	public Optional<Restaurant> getRestaurant(@PathVariable("id") String id) {
		return restaurantRepository.findById(Integer.parseInt(id));
	}
	
	@PutMapping("/update/{id}")
  public Boolean updateRestaurant(@PathVariable("id") String id, @RequestBody Restaurant restaurant) {
    Optional<Restaurant> restaurantOptional = restaurantRepository.findById(Integer.parseInt(id));

    if (restaurantOptional.isPresent()) {
    	restaurantRepository.save(restaurant);
      return true;
    }
    return false;
  }
	
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
