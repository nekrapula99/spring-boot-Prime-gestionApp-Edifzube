package com.edifzube.gestionApp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.edifzube.gestionApp.model.Item;
import com.edifzube.gestionApp.model.Seccion;
import com.edifzube.gestionApp.resources.Constantes;
import com.edifzube.gestionApp.service.ItemService;
import com.edifzube.gestionApp.service.SeccionService;

@Controller
public class ItemController extends Control implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Item item;
	private List<Item> filtered;
	private List<Item> itemsList;
	private List<Seccion> seccionesList;
	private List<Seccion> filteredSeccion;
	
	// Variables Carga de lista
	private String update;
	private String nombreLista;
	private Object objSeCall;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private SeccionService seccionService;
	
	
	@PostConstruct
	public void init() {
		item = new Item();
		getItems();
	}

	
	public void  getItems() {
		itemsList = itemService.getItems();
	}
	
	public void accionNuevo() {
		setTitulo(Constantes.TITULO_NUEVO);
		item = new Item();
		seccionesList = seccionService.getSecciones();
		item.setFecha_creacion(new Date());
	}
	
	public void modificar() {
		try {
			setTitulo(Constantes.TITULO_MODIFICAR);
			seccionesList = seccionService.getSecciones();
			item.setFecha_actualizacion(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Actualizando Item"));
		}
	}
	
	public void save() {
		try {
			itemService.save(item);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Se ha guardado el Item"));
			getItems();
			closeDialog("dlgNuevoUpdate");	
			PrimeFaces.current().ajax().update("formTable");
		}catch (Exception e) {
			if (isDuplicateException(e)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Ya existe un Item con el codigo: "+item.getCoditem()));
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
			item = (Item) event.getComponent().getAttributes().get("item");
			itemService.delete(item.getIditem());
			getItems();;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Se ha eliminado el Item"));
			PrimeFaces.current().ajax().update("formTable");
		} catch (Exception e) {
			if (isReferenceConstraintException(e)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Existen referencias para este item"));
			}else {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Error Eliminando Item"));
				return;
			}
			
		}
		
	}
	
	public List<Seccion> completeSeccion(String query) {
		
		   String queryLowerCase = query.toLowerCase();
	       List<Seccion> listSeccion = seccionService.getSecciones();
	       List<Seccion> results = new ArrayList<Seccion>();  

           for (Seccion obj : listSeccion) {        	
        	if(obj.getCodseccion().toLowerCase().contains(queryLowerCase)){
        		results.add(obj);
        		item.setSeccionid(obj.getIdseccion());
    		}			
		}
        //return listSeccion.stream().filter(t -> t.getCodseccion().toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
        return results;
    }
	
	@SuppressWarnings("rawtypes")
	public void llenarObjectCall(SelectEvent event) {
		if (objSeCall == null) {
			return;
		}
		String strClassLlama = objSeCall.getClass().getSimpleName();
		Item item = (Item) event.getObject();
		
			if (strClassLlama.equalsIgnoreCase("ingresosController")) {
				IngresosController ingresosController = (IngresosController) objSeCall;
					if (nombreLista.equalsIgnoreCase("item")) {
						ingresosController.setCodingresoAux(item.getCoditem());
						ingresosController.setDetalleAux(item.getDescripcion());
						ingresosController.setUnimedidaAux(item.getUnimedida());
						ingresosController.setValorunitarioAux(item.getPrecio());
						ingresosController.setIvaAux(item.getIva());
						
					}
				
			}
		
		update = null;	
		
	}
	
	public List<Item> completeItem(String query) {
		
		   String queryLowerCase = query.toLowerCase();
	       List<Item> listItems = itemService.getItems();
	       List<Item> results = new ArrayList<Item>();  

        for (Item obj : listItems) {        	
	     	if(obj.getCoditem().toLowerCase().contains(queryLowerCase)){
	     		results.add(obj);
	     		//item.setSeccionid(obj.getIdseccion());
	 		}			
		}
     //return listSeccion.stream().filter(t -> t.getCodseccion().toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
     return results;
 }

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getFiltered() {
		return filtered;
	}

	public void setFiltered(List<Item> filtered) {
		this.filtered = filtered;
	}

	public List<Item> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<Item> itemsList) {
		this.itemsList = itemsList;
	}

	public List<Seccion> getSeccionesList() {
		return seccionesList;
	}

	public void setSeccionesList(List<Seccion> seccionesList) {
		this.seccionesList = seccionesList;
	}

	public List<Seccion> getFilteredSeccion() {
		return filteredSeccion;
	}

	public void setFilteredSeccion(List<Seccion> filteredSeccion) {
		this.filteredSeccion = filteredSeccion;
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

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
}
