package com.edifzube.gestionApp.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.edifzube.gestionApp.model.Empleado;
import com.edifzube.gestionApp.resources.Constantes;
import com.edifzube.gestionApp.service.EmpleadoService;

@Controller
public class EmpleadoController extends Control implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Empleado empleado;
	private List<Empleado> filteredEmpleado;
	private List<Empleado> empleadosList;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	
	@PostConstruct
	public void init() {
		empleado = new Empleado();
		getEmpleados();
	}

	
	public void  getEmpleados() {
		empleadosList = empleadoService.getEmpleados();
	}
	
	public void accionNuevo() {
		setTitulo(Constantes.TITULO_NUEVO);
		empleado = new Empleado();
		empleado.setFecha_creacion(new Date());
	}
	
	public void modificar() {
		try {
			setTitulo(Constantes.TITULO_MODIFICAR);
			System.out.println("empleado a modificar: "+empleado);
			empleado.setFecha_actualizacion(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Actualizando empleado"));
		}
	}
	
	public void save() {
		try {
			empleadoService.save(empleado);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Se ha guardado el empleado"));
			getEmpleados();
			closeDialog("dlgNuevoUpdate");	
			PrimeFaces.current().ajax().update("formTable");
		}catch (Exception e) {
			if (isDuplicateException(e)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Ya existe un empleado con la cedula: "+empleado.getCedula()));
				return;
			} else {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Error guardando empleado"));
				return;
			}
		}
			
	
	}
	
	public void remove(ActionEvent event) {
		try {
			empleado = (Empleado) event.getComponent().getAttributes().get("empleado");
			empleadoService.delete(empleado.getIdempleado());
			getEmpleados();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Se ha eliminado el empleado"));
			PrimeFaces.current().ajax().update("formTable");
		} catch (Exception e) {
			if (isReferenceConstraintException(e)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Existen referencias para este empleado"));
			}else {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Error Eliminando Empleado"));
				return;
			}
			
		}
	}

	public List<Empleado> getFilteredEmpleado() {
		return filteredEmpleado;
	}

	public void setFilteredEmpleado(List<Empleado> filteredEmpleado) {
		this.filteredEmpleado = filteredEmpleado;
	}

	public List<Empleado> getEmpleadosList() {
		return empleadosList;
	}

	public void setEmpleadosList(List<Empleado> empleadosList) {
		this.empleadosList = empleadosList;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}
