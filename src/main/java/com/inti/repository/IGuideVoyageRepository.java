package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.model.GuideVoyage;

@Repository
public interface IGuideVoyageRepository extends JpaRepository<GuideVoyage, Integer> {
	
}
