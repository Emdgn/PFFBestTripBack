package com.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inti.model.Pays;
import com.inti.model.Ville;

@Repository
public interface IPaysRepository extends JpaRepository<Pays, Integer>{

	@Query(value = "select * from Pays where id= :id", nativeQuery = true)
	List<Ville> findlisteVillePays(@Param("id") int id);
	
	
}
