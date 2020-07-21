package com.edifzube.gestionApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edifzube.gestionApp.model.Empleado;
import com.edifzube.gestionApp.repository.IEmpleado;

@Service
public class EmpleadoService {
	
	@Autowired
	private IEmpleado empleadoRepository;
	
	public List<Empleado> getEmpleados(){
		return empleadoRepository.findAll();
	}
	
	public void save(Empleado empleado) {
		empleadoRepository.save(empleado);
	}
	
	public Optional<Empleado> findById(int id) {
		System.out.println("empleado servi find: "+id);
		return empleadoRepository.findById(id);
	}
	
	public void delete(Integer id) {
		empleadoRepository.deleteById(id);
	}

}
