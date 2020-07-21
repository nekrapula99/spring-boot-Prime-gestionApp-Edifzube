package com.edifzube.gestionApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edifzube.gestionApp.model.Ingresos;
import com.edifzube.gestionApp.repository.IngresosRepository;

@Service
public class IngresosService {
	
	@Autowired
	private IngresosRepository ingresosRepository;
	
	public List<Ingresos> getIngresos(){
		return ingresosRepository.findAll();
	}
	
	public void save(Ingresos ingresos) {
		ingresosRepository.save(ingresos);
	}
	
	public Optional<Ingresos> findById(int id) {
		return ingresosRepository.findById(id);
	}
	
	public void delete(Integer id) {
		ingresosRepository.deleteById(id);
	}

}
