package com.edifzube.gestionApp.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.edifzube.gestionApp.model.Seccion;
import com.edifzube.gestionApp.service.SeccionService;

@Controller
public class SeccionConverter implements Converter, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SeccionService seccionService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		

		submittedValue = submittedValue.trim();
		
		FacesContext facesContext=FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		Seccion SeccionDisable = (Seccion)session.getAttribute(Seccion.class.getSimpleName());

		if (submittedValue.equals("")) { 
            return new Seccion();
        } else { 
        	
        	for (Seccion p : seccionService.getSecciones()) {  
                if (p.getCodseccion().trim().equals(submittedValue)) {  //Campo de la clave primaria
                    return p;  
                }  
            }  
        	
        	if(SeccionDisable != null && SeccionDisable.getCodseccion().equals(submittedValue)){
            	return (Seccion)SeccionDisable;
        	}
        } 
		
		return new Seccion();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Seccion) value).getCodseccion()); //Campo de la clave primaria 
        } 
	}

}
