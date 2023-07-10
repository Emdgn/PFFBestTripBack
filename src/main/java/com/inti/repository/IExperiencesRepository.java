package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inti.model.Experiences;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface IExperiencesRepository extends JpaRepository<Experiences, Integer> {
	
	@Modifying
	@Query(value = "update Experiences set id_utilisateur=:id_utilisateur where id_experience=:id_experience", nativeQuery = true)
	void insertIdUtilisateur(@Param("id_utilisateur") int id_utilisateur, @Param("id_experience") int id_experiences);
	


}
