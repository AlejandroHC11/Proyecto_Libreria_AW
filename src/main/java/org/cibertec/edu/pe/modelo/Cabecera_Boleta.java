package org.cibertec.edu.pe.modelo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cabecera_Boleta")
public class Cabecera_Boleta {
	
	
	// Campos o atributos
    @Id
    @Column(name = "num_bol", nullable = false, length = 5)
    private String numBol;

    @Column(name = "fch_bol")
    private Date fchBol;

    @Column(name = "cod_cliente", nullable = false)
    private int codCliente;

    @ManyToOne
    @JoinColumn(name = "cod_cliente", referencedColumnName = "codigo", insertable = false, updatable = false)
    private Usuarios cliente;
    
    
 // Métodos Conttructores
	public Cabecera_Boleta(String numBol, Date fchBol, int codCliente, Usuarios cliente) {
		super();
		this.numBol = numBol;
		this.fchBol = fchBol;
		this.codCliente = codCliente;
		this.cliente = cliente;
	}

	public Cabecera_Boleta() {
		super();
	}
	
	
	// Getter and Setter methods
	
	public String getNumBol() {
		return numBol;
	}

	public void setNumBol(String numBol) {
		this.numBol = numBol;
	}

	public Date getFchBol() {
		return fchBol;
	}

	public void setFchBol(Date fchBol) {
		this.fchBol = fchBol;
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public Usuarios getCliente() {
		return cliente;
	}

	public void setCliente(Usuarios cliente) {
		this.cliente = cliente;
	}

    
    
    
    
}
