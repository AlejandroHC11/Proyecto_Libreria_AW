package org.cibertec.edu.pe.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Productos")
public class Productos {

	
	// Campos o atributos
    @Id
    @Column(name = "idprod", nullable = false, length = 5)
    private String idprod;

    @Column(name = "descripcion", length = 45)
    private String descripcion;

    @Column(name = "stock")
    private int stock;

    @Column(name = "precio", precision = 8, scale = 2)
    private BigDecimal precio;

    @Column(name = "idcategoria")
    private int idcategoria;

    @Column(name = "estado")
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria", insertable = false, updatable = false)
    private Categorias categoria;

    
    // Métodos Conttructores
	public Productos(String idprod, String descripcion, int stock, BigDecimal precio, int idcategoria, boolean estado,
			Categorias categoria) {
		super();
		this.idprod = idprod;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
		this.idcategoria = idcategoria;
		this.estado = estado;
		this.categoria = categoria;
	}

	public Productos() {
		super();
	}

	
	// Getter and Setter methods
	public String getIdprod() {
		return idprod;
	}

	public void setIdprod(String idprod) {
		this.idprod = idprod;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}

    
    
    
    
    
    
    
    
    
}
