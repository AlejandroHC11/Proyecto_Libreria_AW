package org.cibertec.edu.pe.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Boletas")
public class Boletas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBoleta;
	@ManyToOne
	@JoinColumn(name = "cliente", referencedColumnName = "usuario")
	private Usuarios cliente;
	private LocalDate fchBol;
	private BigDecimal Total;
	public Boletas(Usuarios cliente, LocalDate fchBol, BigDecimal total) {
		this.cliente = cliente;
		this.fchBol = fchBol;
		Total = total;
	}
	public Boletas() {
	}
	public int getIdBoleta() {
		return idBoleta;
	}
	public void setIdBoleta(int idBoleta) {
		this.idBoleta = idBoleta;
	}
	public Usuarios getCliente() {
		return cliente;
	}
	public void setCliente(Usuarios cliente) {
		this.cliente = cliente;
	}
	public LocalDate getFchBol() {
		return fchBol;
	}
	public void setFchBol(LocalDate fchBol) {
		this.fchBol = fchBol;
	}
	public BigDecimal getTotal() {
		return Total;
	}
	public void setTotal(BigDecimal total) {
		Total = total;
	}
	
}
