package com.inti.repository;

import java.util.List;
import java.util.Optional;

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
	
	@Query(value = "select id_utilisateur from Experiences where id_experience=:id_experience", nativeQuery = true)
	Optional<Integer> getIdUtilisateurByIdExp(@Param("id_experience") int id_experience);

	@Query(value = "select * from experiences where type = :type", nativeQuery = true)
	List<Experiences> getExperiencesByType(@Param("type") String type);
	
	@Query(value = "select id_experience from experiences where type = :type", nativeQuery = true)
	List<Integer> doesExperienceExist(@Param("type") String type);
}
