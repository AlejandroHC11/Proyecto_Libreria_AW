package org.cibertec.edu.pe.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DetalleBoleta")
public class DetalleBoleta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numDetBol")
	private int numDetBol;		
	@ManyToOne
    @JoinColumn(name = "producto", referencedColumnName = "idprod")
	private Productos producto;
	@Column(name = "cantidad")
	private int cantidad;
	@Column(name = "precioVenta")
	private BigDecimal precioVenta;
	public DetalleBoleta(int numDetBol, Productos producto, int cantidad,
			BigDecimal precioVenta) {
		this.numDetBol = numDetBol;
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
