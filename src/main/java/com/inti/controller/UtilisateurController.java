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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Utilisateur;
import com.inti.repository.IUtilisateurRepository;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {

/*
 * Appel de la répository pour pouvoir communiquer avec la base de donées
 */
	@Autowired
	IUtilisateurRepository iur;


	/*
	 * Recupération de l'ensemble des utilisateurs retournée dans une liste
	 */
	@GetMapping("listeUtilisateur")
	public List<Utilisateur> listeUtilisateur()
	{
		return iur.findAll();
	}
	
	
	/*
	 * Modification d'un utilisateur à partir d'un formulaire
	 * Récupération des données inséré dans le formulaire grace à RequestBody
	 * On verifie dans un premier temps que le utillisateur existe dans la base
	 * Puis on modification dans la base de donée avec ipr.save
	 */	
	@PostMapping("saveUtilisateur")
	public Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur)
	{
		return iur.save(utilisateur);
	}
	
	
	/*
	 * Suppresssion d'un utilisateur à partir de son id qui a été transmit dans l'url
	 * Récupération de l'id dans l'url grace à Pathvariable
	 * Supression dans la base de donée avec ipr.delete
	 */	
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
	
	
	/*
	 * Modification d'un utilisateur à partir d'un formulaire
	 * Récupération des données inséré dans le formulaire grace à RequestBody
	 * On verifie dans un premier temps que le utillisateur existe dans la base
	 * Puis on modification dans la base de donée avec ipr.save
	 */		
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
	
	/*
	 * Récupération d'un utilisateur dans la base de données grace à son id
	 * L'id du utillisateur récupérer est transmit par la pathVariable
	 * La récupération dans la base de donnée est faite par la fonction ipr.getReferenceById
	 */
	@GetMapping("getUtilisateur/{id}")
	public Utilisateur getUtilisateur(@PathVariable("id") int id)
	{
		return iur.getReferenceById(id);
	}
	
	/*
	 * Récupération de l'ensembles des emails dans la base de données ou la valeur estAbonne est egale a true
	 * La récupération dans la base de donnée est faite par la fonction ipr.getEmailByEstAbone
	 */
	@GetMapping("getEmailByEstAbonne")
	public List<String> getEmailByEstAbonne()
	{
		return iur.getEmailByEstAbonne();
	}
	
	/*
	 * Récupération de l'utilisateurs qui correspond a un username donné dans la base de données
	 * La récupération dans la base de donnée est faite par la fonction ipr.getUtilisateurByUsername
	 */
	@GetMapping("getUtilisateurByUsername")
	public Utilisateur getUtilisateurByUsername(@RequestParam("username") String username) {
		return iur.getUtilisateurByUsername(username);
	}

}
