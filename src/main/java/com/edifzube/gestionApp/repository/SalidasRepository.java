package com.edifzube.gestionApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edifzube.gestionApp.model.Salidas;

@Repository
public interface SalidasRepository extends JpaRepository<Salidas, Integer>{

}
