package com.edifzube.gestionApp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "ITEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "iditem")
public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IDITEM")
	private Integer iditem;
	
	@Column(name = "CODITEM",length = 50, unique = true)
	private String coditem;
	
	@Column(name = "DESCRIPCION",length = 50)
	private String descripcion;
	
	@Column(name = "UNIMEDIDA",length = 50)
	private String unimedida;
	
	@Column(name = "IVA")
	private int iva;
	
	@Column(name = "PRECIO")
	private double precio;
	
	@Column(name = "PROVEEDOR",length = 50)
	private String proveedor;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION")
	private Date fecha_creacion;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fecha_actualizacion;
	
	@ManyToOne
    @JoinColumn(name = "seccionid", insertable = false, updatable = false)
	private Seccion seccion;
	
	private Integer seccionid;
	
	@ManyToOne
    @JoinColumn(name = "empleadoid", insertable = false, updatable = false)
	private Empleado empleado;
	
	private Integer empleadoid;
	
	public Item() {
    }
	
	public Integer getIditem() {
		return iditem;
	}

	public void setIditem(Integer iditem) {
		this.iditem = iditem;
	}

	public String getCoditem() {
		return coditem;
	}

	public void setCoditem(String coditem) {
		this.coditem = coditem;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUnimedida() {
		return unimedida;
	}

	public void setUnimedida(String unimedida) {
		this.unimedida = unimedida;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	@Override
	public String toString() {
		return "Item [iditem=" + iditem + ", coditem=" + coditem + ", descripcion=" + descripcion + ", unimedida="
				+ unimedida + ", iva=" + iva + ", precio=" + precio + ", proveedor=" + proveedor 
				+", seccionid: "+seccionid+ "]";
	}

	public Integer getSeccionid() {
		return seccionid;
	}
	
	public void setSeccionid(Integer seccionid) {
		this.seccionid = seccionid;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Integer getEmpleadoid() {
		return empleadoid;
	}

	public void setEmpleadoid(Integer empleadoid) {
		this.empleadoid = empleadoid;
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

}
