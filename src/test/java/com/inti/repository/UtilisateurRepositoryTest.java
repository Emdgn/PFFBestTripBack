package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Utilisateur;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UtilisateurRepositoryTest {
	
	@Autowired
	IUtilisateurRepository iur;
	
	@Test
	public void saveUtilisateurWithoutAllArgs() {
		Utilisateur utilisateur = new Utilisateur("username", "mdp", true, "utilisateur", "test@test.fr", "Dupont", "Nicolas");
		utilisateur.setNom("Utilisateur1");
		
		Utilisateur utilisateurSaved = iur.save(utilisateur);
		
		assertThat(utilisateurSaved).isNotNull();
		assertThat(utilisateurSaved.getId()).isGreaterThan(0);
		assertThat(utilisateurSaved.getNom()).isEqualTo("Utilisateur1");
	}
	
	@Test
	public void saveUtilisateurNull() {
		Assertions.assertThrows(Exception.class, () -> iur.save(null));
	}
	
	@Test
	public void afficherUtilisateur() {
		
		Utilisateur utilisateur = new Utilisateur("username", "mdp", true, "utilisateur", "test@test.fr", "Dupont", "Nicolas");
		iur.save(utilisateur);
		
		assertThat(iur.getReferenceById(utilisateur.getId())).isEqualTo(utilisateur);
	}
	
	@Test
	public void afficherListeUtilisateur() {
		Utilisateur u1 = new Utilisateur("username1", "mdp", true, "utilisateur", "test@test.fr", "Dupont", "Nicolas");
		Utilisateur u2 = new Utilisateur("username2", "mdp", true, "utilisateur", "test@test.fr", "Dupont", "Nicolas");
		Utilisateur u3 = new Utilisateur("username3", "mdp", true, "utilisateur", "test@test.fr", "Dupont", "Nicolas");
		
		Utilisateur u1saved = iur.save(u1);
		Utilisateur u2saved = iur.save(u2);
		Utilisateur u3saved = iur.save(u3);
		
		List<Utilisateur> listeUtilisateur = List.of(u1saved, u2saved, u3saved);
		
		assertThat(iur.findAll()).isEqualTo(listeUtilisateur);
		assertThat(iur.findAll().get(0)).isEqualTo(u1saved);
	}
	
	@Test
	public void modifierUtilisateur() {
		Utilisateur utilisateur = new Utilisateur("username", "mdp", true, "utilisateur", "test@test.fr", "Dupont", "Nicolas");
		iur.save(utilisateur);
		
		utilisateur.setMdp("mdp1");
		
		assertThat(utilisateur.getMdp()).isEqualTo("mdp1");
	}
	
	@Test
	public void modifierUtilisateurNull() {
		Utilisateur utilisateur = null;
		
		Assertions.assertThrows(Exception.class, () -> utilisateur.setNom("super utilisateur"));
	}
	
	@Test
	public void supprimerUtilisateur() {
		Utilisateur utilisateur = new Utilisateur("username", "mdp", true, "utilisateur", "test@test.fr", "Dupont", "Nicolas");
		iur.save(utilisateur);
		
		iur.delete(utilisateur);
		
		Assertions.assertThrows(Exception.class, () -> iur.getReferenceById(utilisateur.getId()));
	}

}
