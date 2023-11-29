package org.cibertec.edu.pe.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DetalleBoleta")
public class DetalleBoleta {
	@Id
	@Column(name = "numDetBol")
	private int numDetBol;
	@ManyToOne
    @JoinColumn(name = "cabeceraBoleta", referencedColumnName = "numBol")
	private CabeceraBoleta cabeceraBoleta;
	@ManyToOne
    @JoinColumn(name = "producto", referencedColumnName = "idprod")
	private Productos producto;
	@Column(name = "cantidad")
	private int cantidad;
	@Column(name = "precioVenta")
	private BigDecimal precioVenta;
	public DetalleBoleta(int numDetBol, CabeceraBoleta cabeceraBoleta, Productos producto, int cantidad,
			BigDecimal precioVenta) {
		this.numDetBol = numDetBol;
		this.cabeceraBoleta = cabeceraBoleta;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioVenta = precioVenta;
	}
	public DetalleBoleta() {
	}
	public int getNumDetBol() {
		return numDetBol;
	}
	public void setNumDetBol(int numDetBol) {
		this.numDetBol = numDetBol;
	}
	public CabeceraBoleta getCabeceraBoleta() {
		return cabeceraBoleta;
	}
	public void setCabeceraBoleta(CabeceraBoleta cabeceraBoleta) {
		this.cabeceraBoleta = cabeceraBoleta;
	}
	public Productos getProducto() {
		return producto;
	}
	public void setProducto(Productos producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
	
}
