package org.cibertec.edu.pe.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Detalle_Boleta")
public class Detalle_Boleta {

	
	// Campos o atributos
    @Id
    @ManyToOne
    @JoinColumn(name = "num_bol", referencedColumnName = "numBol")
    private Cabecera_Boleta cabeceraBoleta;

    @Id
    @ManyToOne
    @JoinColumn(name = "idprod", referencedColumnName = "idprod")
    private Productos producto;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "preciovta", precision = 8, scale = 2)
    private BigDecimal precioVenta;

    
 // Métodos Conttructores
	public Detalle_Boleta(Cabecera_Boleta cabeceraBoleta, Productos producto, int cantidad, BigDecimal precioVenta) {
		super();
		this.cabeceraBoleta = cabeceraBoleta;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioVenta = precioVenta;
	}

	public Detalle_Boleta() {
		super();
	}
	
	// Getter and Setter methods

	public Cabecera_Boleta getCabeceraBoleta() {
		return cabeceraBoleta;
	}

	public void setCabeceraBoleta(Cabecera_Boleta cabeceraBoleta) {
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