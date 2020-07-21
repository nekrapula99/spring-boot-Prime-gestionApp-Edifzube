package com.edifzube.gestionApp.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.edifzube.gestionApp.model.UserCredential;

@Named
@ViewScoped
public class VerificarSeccionController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void verificarSesion() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			UserCredential user= (UserCredential)context.getExternalContext().getSessionMap().get("usuario");
			if(user == null) {
				System.out.print("entro a acceso denegado.");
				context.getExternalContext().redirect("access.xhtml");
			}
		} catch (Exception e) {
			// Log
		}
	}
	
	public String cerrarSesion() {
		 System.out.print("user cerrando... ");
		 FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		 return "login?faces-redirect=true";		
	}

}
