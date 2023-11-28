package org.cibertec.edu.pe.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Estados")
public class Estados {

	// Campos o atributos
    @Id
    @Column(name = "idestado", nullable = false)
    private int idestado;

    @Column(name = "descripcion", length = 15)
    private String descripcion;
    
    
 // Métodos Constructores
	public Estados(int idestado, String descripcion) {
		super();
		this.idestado = idestado;
		this.descripcion = descripcion;
	}


	public Estados() {
	super();
	}


	
	// Propiedades de Lectura y Escritura
	
	public int getIdestado() {
		return idestado;
	}


	public void setIdestado(int idestado) {
		this.idestado = idestado;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
	
    
    
}
