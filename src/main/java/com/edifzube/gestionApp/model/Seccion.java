package com.edifzube.gestionApp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "SECCION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seccion implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IDSECCION")
	private Integer idseccion;
	
	@Column(name = "CODSECCION",length = 50, unique = true)
	private String codseccion;
	
	@Column(name = "NOMBRESECCION",length = 50)
	private String nombreseccion;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION")
	private Date fecha_creacion;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fecha_actualizacion;
	
	@OneToMany(mappedBy = "seccion", fetch = FetchType.EAGER)
	private List<Item> listItems;
	
	
	public Seccion() {
    }

	public Seccion(Integer idseccion, String codseccion, String nombreseccion) {
		this.idseccion = idseccion;
		this.codseccion = codseccion;
		this.nombreseccion = nombreseccion;
	}

	public Integer getIdseccion() {
		return idseccion;
	}

	public void setIdseccion(Integer idseccion) {
		this.idseccion = idseccion;
	}

	public String getCodseccion() {
		return codseccion;
	}

	public void setCodseccion(String codseccion) {
		this.codseccion = codseccion;
	}

	public String getNombreseccion() {
		return nombreseccion;
	}

	public void setNombreseccion(String nombreseccion) {
		this.nombreseccion = nombreseccion;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Date getFecha_actualizacion() {
		return fecha_actualizacion;
	}

	public void setFecha_actualizacion(Date fecha_actualizacion) {
		this.fecha_actualizacion = fecha_actualizacion;
	}

	public List<Item> getListItems() {
		return listItems;
	}

	public void setListItems(List<Item> listItems) {
		this.listItems = listItems;
	}

	@Override
	public String toString() {
		return "Seccion [idseccion=" + idseccion + ", codseccion=" + codseccion + ", nombreseccion=" + nombreseccion
				+ ", listItems=" + listItems+"]";
	}
	

}
