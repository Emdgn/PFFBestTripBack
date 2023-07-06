package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inti.model.Activite;

public interface ActiviteRepository extends JpaRepository<Activite, Integer> {

}
