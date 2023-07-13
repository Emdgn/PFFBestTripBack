package com.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inti.model.GuideVoyage;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface IGuideVoyageRepository extends JpaRepository<GuideVoyage, Integer> {
	

	@Query(value = "select * from guide_voyage where nom = :nom", nativeQuery = true)
	List<GuideVoyage> getGuideByLocalisation(@Param("nom") String nom);
	
	@Query(value = "select id_guide from guide_voyage where nom = :nom", nativeQuery = true)
	List<Integer> doesGuideExist(@Param("nom") String nom);
	
	@Modifying
	@Query(value = "update guide_voyage set nbr_de_note = (nbr_de_note+1), somme_note_tot = somme_note_tot + :notation where id_guide = :idGuide", nativeQuery = true)
	void insertNbrSommeNoteGuide(@Param("idGuide") int idGuide, @Param("notation") int notation);
	
	@Modifying
	@Query(value = "update guide_voyage set note = round(somme_note_tot/nbr_de_note, 1) where id_guide = :idGuide", nativeQuery = true)
	void insertNoteGuide(@Param("idGuide") int idGuide);


}
