package com.edifzube.gestionApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edifzube.gestionApp.model.Ingresos;

@Repository
public interface IngresosRepository extends JpaRepository<Ingresos, Integer>{

}
