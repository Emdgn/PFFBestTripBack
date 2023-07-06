package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.model.Pays;

@Repository
public interface IPaysRepository extends JpaRepository<Pays, Integer>{

}
