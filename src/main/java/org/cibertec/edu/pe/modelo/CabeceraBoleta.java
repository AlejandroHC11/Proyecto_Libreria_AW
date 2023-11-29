package org.cibertec.edu.pe.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CabeceraBoleta")
public class CabeceraBoleta {
	@Id
	@Column(name = "numBol")
	private int numBol;
	@Column(name = "fchBol")
	private Date fchBol;
	@ManyToOne
	@JoinColumn(name = "cliente", referencedColumnName = "usuario")
	private Usuarios cliente;
	public CabeceraBoleta(int numBol, Date fchBol, Usuarios cliente) {
		this.numBol = numBol;
		this.fchBol = fchBol;
		this.cliente = cliente;
	}
	public CabeceraBoleta() {
	}
	
	
	public int getNumBol() {
		return numBol;
	}
	public void setNumBol(int numBol) {
		this.numBol = numBol;
	}
	public Date getFchBol() {
		return fchBol;
	}
	public void setFchBol(Date fchBol) {
		this.fchBol = fchBol;
	}
	public Usuarios getCliente() {
		return cliente;
	}
	public void setCliente(Usuarios cliente) {
		this.cliente = cliente;
	}
	
	
}
