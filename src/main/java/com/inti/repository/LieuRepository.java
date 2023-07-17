package com.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inti.model.Lieu;

/**
 * Repository de la classe Lieu
 *
 */
public interface LieuRepository extends JpaRepository<Lieu, Integer>{
	// Requête pour récupérer les identifiants des lieux pour une expérience donnée
	@Query(value = "SELECT l.id_activite FROM lieu l JOIN activite a ON l.id_activite = a.id WHERE a.id_experience=:id_experience", nativeQuery = true)
	List<Integer> getByIdExperience(@Param("id_experience") int id_experience);
}
