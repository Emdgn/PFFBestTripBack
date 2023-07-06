package com.inti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Utilisateur;
import com.inti.repository.IUtilisateurRepository;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {
	
	@Autowired
	IUtilisateurRepository iur;
	
	@GetMapping("listeUtilisateur")
	public List<Utilisateur> listeUtilisateur()
	{
		return iur.findAll();
	}
	
	@PostMapping("saveUtilisateur")
	public Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur)
	{
		return iur.save(utilisateur);
	}
	
	@DeleteMapping("deleteUtilisateur/{id}")
	public boolean deleteUtilisateur(@PathVariable("id") int id)
	{
		try {
			Utilisateur u = iur.findById(id).get();
			iur.delete(u);
			return true;
		} catch (Exception e) {
		return false;
		}
	}
	
	@PutMapping("modifierUtilisateur")
	public boolean modifierUtilisateur(@RequestBody Utilisateur u)
	{
		if(iur.getReferenceById(u.getId()) != null)
		{
			iur.save(u);
			return true;
		}
		return false;
	}
	
	@GetMapping("getUtilisateur/{id}")
	public Utilisateur getUtilisateur(@PathVariable("id") int id)
	{
		return iur.getReferenceById(id);
	}

}
