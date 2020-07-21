package com.edifzube.gestionApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edifzube.gestionApp.model.Seccion;
import com.edifzube.gestionApp.repository.SeccionRepository;

@Service
public class SeccionService {
	
	@Autowired
	private SeccionRepository seccionRepository;
	
	public List<Seccion> getSecciones(){
		return seccionRepository.findAll();
	}
	
	public void save(Seccion seccion) {
		seccionRepository.save(seccion);
	}
	
	public Optional<Seccion> findById(int id) {
		return seccionRepository.findById(id);
	}
	
	public void delete(Integer id) {
		seccionRepository.deleteById(id);
	}


}
