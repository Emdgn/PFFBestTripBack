package com.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inti.model.Ville;

/**
 * Repository de la classe Ville
 *
 */
@Repository
public interface IVilleRepository extends JpaRepository<Ville, Integer>{
  // Requête pour récupérer les villes d'un pays donné
	@Query(value = "select * from Ville where id_pays= :id", nativeQuery = true)
	List<Ville> findlisteVillePays(@Param("id") int id);

}
