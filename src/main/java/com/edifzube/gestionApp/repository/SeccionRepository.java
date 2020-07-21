package com.edifzube.gestionApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edifzube.gestionApp.model.Seccion;


@Repository
public interface SeccionRepository extends JpaRepository<Seccion, Integer>{

}
