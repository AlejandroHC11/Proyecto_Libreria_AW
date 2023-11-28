package org.cibertec.edu.pe.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Categorias")
public class Categorias {
  
	
	// Campos o atributos
    @Id
    @Column(name = "idcategoria", nullable = false)
    private int idcategoria;

    @Column(name = "descripción", length = 45)
    private String descripcion;

    
    // Métodos Constructores
	public Categorias(int idcategoria, String descripcion) {
		super();
		this.idcategoria = idcategoria;
		this.descripcion = descripcion;
	}


	public Categorias() {
		super();
	}

	// Getter and Setter methods
	
	public int getIdcategoria() {
		return idcategoria;
	}


	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    
	
	
	
	
	
	
	
}
