package com.inti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inti.model.Utilisateur;

@Repository
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	
	@Query(value = "select id from Utilisateur where username= :username", nativeQuery = true)
	Optional<Integer> getIdByUsername(@Param("username") String username);
	
	@Query(value = "select username from utilisateur where id=:id", nativeQuery = true)
	String getUsernameById(@Param("id") int id);
	
	@Query(value = "select email from Utilisateur where estAbonne = 1", nativeQuery = true)
	List<String> getEmailByEstAbonne();

}
