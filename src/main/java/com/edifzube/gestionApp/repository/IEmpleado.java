package com.edifzube.gestionApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edifzube.gestionApp.model.Empleado;

@Repository
public interface IEmpleado extends JpaRepository<Empleado, Integer>{

}
