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

import com.inti.model.Experiences;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ExperiencesRepositoryTest {
	
	@Autowired
	IExperiencesRepository ier;
	
	
	@Test
	public void saveExperiencesWithoutAllArgs() {
		Experiences experiences = new Experiences();
		experiences.setNom("Experiences1");
		
		Experiences experiencesSaved = ier.save(experiences);
		
		assertThat(experiencesSaved).isNotNull();
		assertThat(experiencesSaved.getIdExperience()).isGreaterThan(0);
		assertThat(experiencesSaved.getNom()).isEqualTo("Experiences1");
	}
	
	@Test
	public void saveExperiencesNull() {
		Assertions.assertThrows(Exception.class, () -> ier.save(null));
	}
	
	@Test
	public void afficherExperiences() {
		
		Experiences experiences = new Experiences("e", LocalDate.of(2022, 01, 01), LocalDate.of(2022, 02, 02), "montagne");
		ier.save(experiences);
		
		assertThat(ier.getReferenceById(experiences.getIdExperience())).isEqualTo(experiences);
	}
	
	@Test
	public void afficherListeExperiences() {
		Experiences e1 = new Experiences();
		Experiences e2 = new Experiences();
		Experiences e3 = new Experiences();
		
		Experiences e1saved = ier.save(e1);
		Experiences e2saved = ier.save(e2);
		Experiences e3saved = ier.save(e3);
		
		List<Experiences> listeExperiences = List.of(e1saved, e2saved, e3saved);
		
		assertThat(ier.findAll()).isEqualTo(listeExperiences);
		assertThat(ier.findAll().get(0)).isEqualTo(e1saved);
	}
	
	@Test
	public void modifierExperiences() {
		Experiences experiences = new Experiences("e", LocalDate.of(2022, 01, 01), LocalDate.of(2022, 02, 02), "montagne");
		ier.save(experiences);
		
		experiences.setType("plage");
		
		assertThat(experiences.getType()).isEqualTo("plage");
	}
	
	@Test
	public void modifierExperiencesNull() {
		Experiences experiences = null;
		
		Assertions.assertThrows(Exception.class, () -> experiences.setNom("super expérience"));
	}
	
	@Test
	public void supprimerExperiences() {
		Experiences experiences = new Experiences();
		ier.save(experiences);
		
		ier.delete(experiences);
		
		Assertions.assertThrows(Exception.class, () -> ier.getReferenceById(experiences.getIdExperience()));
	}

}
