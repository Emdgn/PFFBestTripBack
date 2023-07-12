package com.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inti.model.GuideVoyage;

@Repository
public interface IGuideVoyageRepository extends JpaRepository<GuideVoyage, Integer> {
	
	@Query(value = "select * from guide_voyage where nom = :nom", nativeQuery = true)
	List<GuideVoyage> getGuideByLocalisation(@Param("nom") String nom);

}
