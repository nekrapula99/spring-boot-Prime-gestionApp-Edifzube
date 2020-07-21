package com.edifzube.gestionApp.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.edifzube.gestionApp.model.Seccion;
import com.edifzube.gestionApp.resources.Constantes;
import com.edifzube.gestionApp.service.SeccionService;

@Controller
@Named
@RequestScoped
public class SeccionController extends Control implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Seccion seccion;
	private List<Seccion> filtered;
	private List<Seccion> seccionesList;
	
	// Variables Carga de lista
	private String update;
	private String nombreLista;
	private Object objSeCall;
	
	@Autowired
	private SeccionService seccionService;
	
	
	@PostConstruct
	public void init() {
		seccion = new Seccion();
		getSecciones();
	}

	
	public void  getSecciones() {
		setSeccionesList(seccionService.getSecciones());
		System.out.println("Lista SEcciones: "+getSeccionesList());
	}
	
	public void accionNuevo() {
		setTitulo(Constantes.TITULO_NUEVO);
		seccion = new Seccion();
		seccion.setFecha_creacion(new Date());
	}
	
	public void modificar() {
		try {
			setTitulo(Constantes.TITULO_MODIFICAR);
			seccion.setFecha_actualizacion(new Date());
		
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Actualizando seccion"));
		}
	}
	
	public void save() {
		try {
			seccionService.save(seccion);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Se ha guardado la Sección"));
			getSecciones();
			closeDialog("dlgNuevoUpdate");	
			PrimeFaces.current().ajax().update("formTable");
		}catch (Exception e) {
			if (isDuplicateException(e)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Ya existe una Sección con el código: "+seccion.getCodseccion()));
				return;
			} else {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Error guardando Sección"));
				return;
			}
		}
			
	
	}
	
	public void remove(ActionEvent event) {
		try {
			seccion = (Seccion) event.getComponent().getAttributes().get("seccion");
			seccionService.delete(seccion.getIdseccion());
			getSecciones();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Se ha eliminado la Seccón"));
			PrimeFaces.current().ajax().update("formTable");
		} catch (Exception e) {
			if (isReferenceConstraintException(e)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Existen referencias para esta sección"));
			}else {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Error Eliminando Sección"));
				return;
			}
			
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public void llenarObjectCall(SelectEvent event) {
		if (objSeCall == null) {
			return;
		}
		String strClassLlama = objSeCall.getClass().getSimpleName();
		Seccion seccion = (Seccion) event.getObject();
		
			if (strClassLlama.equalsIgnoreCase("itemController")) {
				ItemController itemController = (ItemController) objSeCall;
					if (nombreLista.equalsIgnoreCase("seccion")) {
						itemController.getItem().setSeccionid(seccion.getIdseccion());
						itemController.getItem().setSeccion((Seccion) event.getObject());
					}
				
			}
		
		update = null;	
		
	}

	public List<Seccion> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<Seccion> filtered) {
		this.filtered = filtered;
	}

	public List<Seccion> getSeccionesList() {
		return seccionesList;
	}

	public void setSeccionesList(List<Seccion> seccionesList) {
		this.seccionesList = seccionesList;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getNombreLista() {
		return nombreLista;
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}

	public Object getObjSeCall() {
		return objSeCall;
	}

	public void setObjSeCall(Object objSeCall) {
		this.objSeCall = objSeCall;
	}


}
