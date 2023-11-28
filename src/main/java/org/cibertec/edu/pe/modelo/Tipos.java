package org.cibertec.edu.pe.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tipos")
public class Tipos {

	// Campos o atributos
    @Id
    @Column(name = "idtipo", nullable = false)
    private int idtipo;

    @Column(name = "descripción", length = 15)
    private String descripcion;
    
    
 // Métodos Constructores

	public Tipos(int idtipo, String descripcion) {
		super();
		this.idtipo = idtipo;
		this.descripcion = descripcion;
	}

	public Tipos() {
		super();
	}
	
	
	// Propiedades de Lectura y Escritura

	public int getIdtipo() {
		return idtipo;
	}

	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    
}
