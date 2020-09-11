package com.edifzube.gestionApp.controller;


import javax.annotation.PostConstruct;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;
import org.springframework.stereotype.Controller;

import com.edifzube.gestionApp.resources.Constantes;

import java.util.ResourceBundle;

@Controller
public class Control {
	
	
	private static ResourceBundle resources;
	
	private String titulo;
	
	@PostConstruct
	public void controlPost(){
		
		//resources = Constantes.rb;
		//System.out.print("Resuorces: "+resources);
		//String test = resources.getString("grid_total_registros");
	}
	
	
	public void evtCloseDialog(CloseEvent event) {
        try { 
        	FacesContext context = FacesContext.getCurrentInstance(); 
        	String viewId = context.getViewRoot().getViewId();
        	ViewHandler handler = context.getApplication().getViewHandler();
        	UIViewRoot root = handler.createView(context, viewId);
        	root.setViewId(viewId); 
        	context.setViewRoot(root);
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * Cierra dialog jsf
	 * @param id
	 */
	public static void closeDialog(String id){
		try {
			PrimeFaces.current().executeScript("PF('"+id+"').hide()");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Válida si la exepcion corresponde a una clave duplicada
	 */
	public static boolean isDuplicateException(Throwable ex){
		boolean validate = false;
		try {			
			
			if(ex.getCause().getCause().getMessage().contains("Duplicate entry")){
				validate = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return validate;
	}
	
	/*
	 * Válida si la exepcion corresponde a restriccion REFERENCE constraint
	 */
	public static boolean isReferenceConstraintException(Throwable ex){
		boolean validate = false;
		try {			
			
			if(ex.getCause().getCause().getMessage().contains("REFERENCES")){
				validate = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return validate;
	}

	public static ResourceBundle getResources() {
		return resources;
	}

	public static void setResources(ResourceBundle resources) {
		Control.resources = resources;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



}
