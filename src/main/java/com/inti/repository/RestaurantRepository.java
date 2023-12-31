package com.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inti.model.Restaurant;

/**
 * Repository de la classe Restaurant
 *
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	// Requête pour récupérer les identifiants des restaurants pour une expérience donnée
	@Query(value = "SELECT r.id_activite FROM restaurant r JOIN activite a ON r.id_activite = a.id WHERE a.id_experience=:id_experience", nativeQuery = true)
	List<Integer> getByIdExperience(@Param("id_experience") int id_experience);
}
