package com.edifzube.gestionApp.controller;

import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.edifzube.gestionApp.model.UserCredential;

@Named
@ViewScoped
@Controller
@SessionScoped
public class LoginController {
	
	private static final Log LOGGER = LogFactory.getLog(LoginController.class);
	
	
	@GetMapping("/")
	public String redirectToLogin() {
		return "redirect:/login.xhtml";
	}
	
	@GetMapping("/login.xhtml")
	public String showLoginForm(Model model,
			@RequestParam(name="error", required = false) String error) {
		model.addAttribute("error", error);
		model.addAttribute("userCredentials", new UserCredential());

		return "login.xhtml";
	}

	public String loginCheck(@ModelAttribute(name="userCredentials") UserCredential userCredential) {
		if(userCredential.getUsername().equals("admin") && userCredential.getPassword().equals("admin")) {
			LOGGER.info("BIENVENIDO: "+userCredential.getUsername());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", userCredential.getUsername());
			return "index?faces-redirect=true";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Credenciales incorrectas"));	
		}
		
		return null;
	}

}
