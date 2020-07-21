package com.edifzube.gestionApp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "INGRESOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingresos implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IDINGRESO")
	private Integer idingreso;
	
	@Column(name = "CODINGRESO", length = 50)
	private String codingreso;
	
	@Column(name = "DETALLE", length = 50)
	private String detalle;
	
	@Column(name = "CANTIDAD")
	private int cantidad;
	
	@Column(name = "UNIMEDIDA",length = 50)
	private String unimedida;
	
	@Column(name = "VALORUNITARIO")
	private double valorunitario;
	
	@Column(name = "IVA")
	private int iva;
	
	@Column(name = "VALORTOTAL")
	private double valortotal;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)  //CHEKEAR EL CAMPO DATE NO ME ESTA PERMITIENDO CREAR REGISTROS NUEVOS - checkar tamaño
	@Column(name = "FECHAINGRESO")
	private Date fechaingreso;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION")
	private Date fecha_creacion;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fecha_actualizacion;

	public Integer getIdingreso() {
		return idingreso;
	}

	public void setIdingreso(Integer idingreso) {
		this.idingreso = idingreso;
	}

	public String getCodingreso() {
		return codingreso;
	}

	public void setCodingreso(String codingreso) {
		this.codingreso = codingreso;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

	public double getValorunitario() {
		return valorunitario;
	}

	public void setValorunitario(double valorunitario) {
		this.valorunitario = valorunitario;
	}

	public double getValortotal() {
		return valortotal;
	}

	public void setValortotal(double valortotal) {
		this.valortotal = valortotal;
	}

	public Date getFechaingreso() {
		return fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
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

	@Override
	public String toString() {
		return "Ingresos [idingreso=" + idingreso + ", codingreso=" + codingreso + ", detalle=" + detalle
				+ ", cantidad=" + cantidad + ", valorunitario=" + valorunitario + ", valortotal=" + valortotal
				+", iva=" + iva+ ", fechaingreso=" + fechaingreso + "]";
	}

}
