package com.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inti.model.Utilisateur;

@Repository
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	
	@Query(value = "select id from Utilisateur where username= :username", nativeQuery = true)
	List<Integer> getIdByUsername(@Param("username") String username);
	
	@Query(value = "select username from utilisateur where id=:id", nativeQuery = true)
	String getUsernameById(@Param("id") int id);
	
	@Query(value = "select email from Utilisateur where est_abonne = 1", nativeQuery = true)
	List<String> getEmailByEstAbonne();
	
	@Query(value = "select * from Utilisateur where username=:username", nativeQuery = true)
	Utilisateur getUtilisateurByUsername(@Param("username") String username);
	
	@Query(value = "select u.* from utilisateur u join guide_voyage_utilisateur g on u.id=g.id_utilisateur where g.id_guide_voyage=:id_guide_voyage", nativeQuery = true)
	List<Utilisateur> getListeUtilisateurByGVId (@Param("id_guide_voyage") int id_guide_voyage);

}
