package com.edifzube.gestionApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edifzube.gestionApp.model.Salidas;
import com.edifzube.gestionApp.repository.SalidasRepository;

@Service
public class SalidasService {
	
	@Autowired
	private SalidasRepository salidasRepository;
	
	public List<Salidas> getSalidas(){
		return salidasRepository.findAll();
	}
	
	public void save(Salidas salidas) {
		salidasRepository.save(salidas);
	}
	
	public Optional<Salidas> findById(int id) {
		return salidasRepository.findById(id);
	}
	
	public void delete(Integer id) {
		salidasRepository.deleteById(id);
	}

}
