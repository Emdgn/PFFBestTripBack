package com.inti.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import com.inti.repository.IUtilisateurRepository;

@WebMvcTest(controllers = UtilisateurController.class)
public class UtilisateurControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	IUtilisateurRepository iur;
	
	@Test
	public void listeUtilisateur() throws Exception {
		mock.perform(get("/listeUtilisateur"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void deleteUtilisateur() throws Exception {
		mock.perform(delete("/deleteUtilisateur/1"))
		.andExpect(status().isOk());
	}

}
