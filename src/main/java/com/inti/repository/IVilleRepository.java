package com.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.model.Ville;

@Repository
public interface IVilleRepository extends JpaRepository<Ville, Integer>{

	public List<Ville> findAllVillePays(int id){
		List<Ville>listeVillePays ;
		return listeVillePays;
	}
}
