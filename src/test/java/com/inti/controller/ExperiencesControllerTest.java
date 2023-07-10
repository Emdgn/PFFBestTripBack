package com.inti.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.inti.model.Experiences;
import com.inti.repository.ActiviteRepository;
import com.inti.repository.IExperiencesRepository;
import com.inti.repository.IUtilisateurRepository;

@WebMvcTest(controllers = ExperiencesController.class)
public class ExperiencesControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private IExperiencesRepository ier;
	@MockBean
	ActiviteRepository ar;
	@MockBean
	IUtilisateurRepository iur;
	
	
//	@Test
//	public void saveExperiences() throws Exception {
//		mock.perform(post("/saveExperiences")
//		.sessionAttr("experiences", new Experiences("e", LocalDate.of(2022, 01, 01), LocalDate.of(2022, 02, 02), "montagne")))
//		.andExpect(status().isOk());
//	}
	
	@Test
	public void listeExperiences() throws Exception {
		mock.perform(get("/listeExperiences"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
//	@Test
//	public void modifierExperiences() throws Exception {
//		mock.perform(put("/updateExperiences")
//		.sessionAttr("experiences", new Experiences("e", LocalDate.of(2022, 01, 01), LocalDate.of(2022, 02, 02), "montagne")))
//		.andExpect(status().isOk());
//	}

}
