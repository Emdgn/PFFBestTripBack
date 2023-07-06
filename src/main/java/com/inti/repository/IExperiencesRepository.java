package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.model.Experiences;

@Repository
public interface IExperiencesRepository extends JpaRepository<Experiences, Integer> {

}
