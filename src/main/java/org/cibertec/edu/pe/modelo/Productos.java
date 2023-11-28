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
    private int idprod;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "descripcion", length = 45)
    private String descripcion;

    @Column(name = "stock")
    private int stock;

    @Column(name = "precio", precision = 8, scale = 2)
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
    private Categorias categoria;

    @Column(name = "estado")
    private boolean estado;


    // Métodos Constructores
    public Productos(int idprod, String imagen, String descripcion, int stock, BigDecimal precio,
			Categorias categoria, boolean estado) {
		this.idprod = idprod;
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
		this.categoria = categoria;
		this.estado = estado;
	}
    

    public Productos() {
	}

    // Getter and Setter methods
	public int getIdprod() {
		return idprod;
	}


	public void setIdprod(int idprod) {
		this.idprod = idprod;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
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


	public Categorias getCategoria() {
		return categoria;
	}


	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


    
    
    
    
    
    
    
    
    
    
}
