package com.edifzube.gestionApp.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.edifzube.gestionApp.model.Salidas;
import com.edifzube.gestionApp.model.Item;
import com.edifzube.gestionApp.resources.Constantes;
import com.edifzube.gestionApp.service.SalidasService;
import com.edifzube.gestionApp.service.ItemService;

@Controller
public class SalidasController extends Control implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SalidasService salidasService;

	@Autowired
	private ItemService itemService;
	
	private Salidas salida;
	private List<Salidas> filtered;
	private List<Salidas> salidasList;
	private List<Item> itemsList;
	private List<Item> filteredItems;
	private boolean flagNuevo;
	private String codigoItem;
	private String detalleItem;
	
	//Calculo de valores
	private String codingresoAux;
	private String detalleAux;
	private String unimedidaAux;
	private int ivaAux;
	private double valorunitarioAux;
	private double valortotalAux;
	private int cantidadAux;
	
	private String accion;
	

	
	@PostConstruct
	public void init() {
		salida = new Salidas();
		flagNuevo = false;
		getSalidas();
	}

	
	public void  getSalidas() {
		salidasList = salidasService.getSalidas();
	}
	
	public void accionNuevo() {
		setAccion(Constantes.ACCION_NUEVO);
		setTitulo(Constantes.TITULO_NUEVO);
		salida = new Salidas();
		itemsList = itemService.getItems();
		salida.setFecha_creacion(new Date());
		salida.setFechasalida(new Date());
		flagNuevo = true;
		codingresoAux = null;
		detalleAux = null;
		unimedidaAux = null;
		ivaAux = 0;
		valorunitarioAux = 0.0;
		valortotalAux = 0.0;
	}
	
	public void modificar() {
		try {
//			setAccion(Constantes.ACCION_MODIFICAR);
//			flagNuevo = false;
//			setTitulo(Constantes.TITULO_MODIFICAR);
//			itemsList = itemService.getItems();
//			salida.setFecha_actualizacion(new Date());
//			codingresoAux = salida.getCodingreso();
//			detalleAux = salida.getDetalle();
//			unimedidaAux = ingreso.getUnimedida();
//			ivaAux = ingreso.getIva();
//			valorunitarioAux = ingreso.getValorunitario();
//			valortotalAux = ingreso.getValortotal();
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Actualizando Ingreso"));
		}
	}
	
	public void save() {
		try {
			if(getAccion().equals(Constantes.ACCION_NUEVO)) {
//				ingreso.setCodingreso(codingresoAux);
//				ingreso.setDetalle(detalleAux);
//				ingreso.setCantidad(cantidadAux);
//				ingreso.setUnimedida(unimedidaAux);
//				ingreso.setIva(ivaAux);
//				ingreso.setValorunitario(valorunitarioAux);
//				ingreso.setValortotal(valortotalAux);
			}else {
				if(getAccion().equals(Constantes.ACCION_MODIFICAR)) {
//				ingreso.setCodingreso(codingresoAux);
//				ingreso.setDetalle(detalleAux);
//				ingreso.setCantidad(cantidadAux);
//				ingreso.setUnimedida(unimedidaAux);
//				ingreso.setIva(ivaAux);
//				ingreso.setValorunitario(valorunitarioAux);
//				ingreso.setValortotal(valortotalAux);
				}
			}
			System.out.println("salida en save: "+salida);
			salidasService.save(salida);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Se ha guardado el Ingreso"));
			getSalidas();
			closeDialog("dlgNuevoUpdate");	
			PrimeFaces.current().ajax().update("formTable");
		}catch (Exception e) {
			if (isDuplicateException(e)) {
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Ya existe un Ingreso con el codigo: "+salida.get ingreso()));
				return;
			} else {
				e.printStackTrace();
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Error guardando empleado"));
				return;
			}
		}
			
	
	}
	
	public void remove(ActionEvent event) {
		try {
			salida = (Salidas) event.getComponent().getAttributes().get("salida");
			salidasService.delete(salida.getIdsalida());
			getSalidas();;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info","Se ha eliminado el Ingreso"));
			PrimeFaces.current().ajax().update("formTable");
		} catch (Exception e) {
			if (isReferenceConstraintException(e)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Existen referencias para este Ingreso"));
			}else {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Error Eliminando Item"));
				return;
			}
			
		}
		
	}
	
	public void cantidadChanged(ValueChangeEvent event) {
        int cantidad = ((int) ((UIOutput) event.getSource()).getValue());
        cantidadAux = cantidad;
        valortotalAux = cantidad*(ivaAux*0.01)*valorunitarioAux+valorunitarioAux*cantidad;

    }
	
	public void valorUniChanged(ValueChangeEvent event) {
        Double valorUnitario = ((Double) ((UIOutput) event.getSource()).getValue());
        valorunitarioAux = valorUnitario;
        if(cantidadAux>0) {
        	valortotalAux = cantidadAux*(ivaAux*0.01)*valorUnitario+valorUnitario*cantidadAux;
        }else {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Debe digitarla cantidad"));
        }
        
    }
	
	public void ivaChanged(ValueChangeEvent event) {
        int iva = ((int) ((UIOutput) event.getSource()).getValue());
        ivaAux = iva;
        valortotalAux = cantidadAux*(iva*0.01)*valorunitarioAux+valorunitarioAux*cantidadAux;         
    }



	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public List<Item> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<Item> itemsList) {
		this.itemsList = itemsList;
	}


	public List<Item> getFilteredItems() {
		return filteredItems;
	}

	public void setFilteredItems(List<Item> filteredItems) {
		this.filteredItems = filteredItems;
	}

	public boolean isFlagNuevo() {
		return flagNuevo;
	}

	public void setFlagNuevo(boolean flagNuevo) {
		this.flagNuevo = flagNuevo;
	}

	public String getCodigoItem() {
		return codigoItem;
	}

	public void setCodigoItem(String codigoItem) {
		this.codigoItem = codigoItem;
	}

	public String getDetalleItem() {
		return detalleItem;
	}

	public void setDetalleItem(String detalleItem) {
		this.detalleItem = detalleItem;
	}

	public String getCodingresoAux() {
		return codingresoAux;
	}

	public void setCodingresoAux(String codingresoAux) {
		this.codingresoAux = codingresoAux;
	}

	public String getDetalleAux() {
		return detalleAux;
	}

	public void setDetalleAux(String detalleAux) {
		this.detalleAux = detalleAux;
	}

	public String getUnimedidaAux() {
		return unimedidaAux;
	}

	public void setUnimedidaAux(String unimedidaAux) {
		this.unimedidaAux = unimedidaAux;
	}

	public double getValorunitarioAux() {
		return valorunitarioAux;
	}

	public void setValorunitarioAux(double valorunitarioAux) {
		this.valorunitarioAux = valorunitarioAux;
	}

	public double getValortotalAux() {
		return valortotalAux;
	}

	public void setValortotalAux(double valortotalAux) {
		this.valortotalAux = valortotalAux;
	}

	public int getIvaAux() {
		return ivaAux;
	}

	public void setIvaAux(int ivaAux) {
		this.ivaAux = ivaAux;
	}

	public int getCantidadAux() {
		return cantidadAux;
	}

	public void setCantidadAux(int cantidadAux) {
		this.cantidadAux = cantidadAux;
	}


	public String getAccion() {
		return accion;
	}


	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	///////////////////////////////

	public SalidasService getSalidasService() {
		return salidasService;
	}


	public void setSalidasService(SalidasService salidasService) {
		this.salidasService = salidasService;
	}


	public Salidas getSalida() {
		return salida;
	}


	public void setSalida(Salidas salida) {
		this.salida = salida;
	}


	public List<Salidas> getFiltered() {
		return filtered;
	}


	public void setFiltered(List<Salidas> filtered) {
		this.filtered = filtered;
	}


	public List<Salidas> getSalidasList() {
		return salidasList;
	}


	public void setSalidasList(List<Salidas> salidasList) {
		this.salidasList = salidasList;
	}
	
	
	
	

}
