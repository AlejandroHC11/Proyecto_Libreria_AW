package org.cibertec.edu.pe.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
	@Table(name = "Usuarios")
	public class Usuarios {

	// Campos o atributos
	@Id
	@Column(name = "codigo")
	    private int codigo;

	    @Column(name = "nombre")
	    private String nombre;

	    @Column(name = "apellido")
	    private String apellido;

	    @Column(name = "usuario", nullable = false)
	    private String usuario;

	    @Column(name = "clave")
	    private String clave;

	    @Column(name = "edad", nullable = false)
	    private int edad;

	    @ManyToOne
	    @JoinColumn(name = "tipo", referencedColumnName = "idtipo")
	    private Tipos tipo;
	    
	    @ManyToOne
	    @JoinColumn(name = "estado", referencedColumnName = "idestado")
	    private Estados estado;

	    
	 // Métodos Constructores		
	    public Usuarios(int codigo, String nombre, String apellido, String usuario, String clave, int edad, Tipos tipos,
				Estados estados) {
			this.codigo = codigo;
			this.nombre = nombre;
			this.apellido = apellido;
			this.usuario = usuario;
			this.clave = clave;
			this.edad = edad;
			this.tipo = tipos;
			this.estado = estados;
		}
				
		public Usuarios() {
		}

		// Propiedades de Lectura y Escritura
		public int getCodigo() {
			return codigo;
		}

		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public String getClave() {
			return clave;
		}

		public void setClave(String clave) {
			this.clave = clave;
		}

		public int getEdad() {
			return edad;
		}

		public void setEdad(int edad) {
			this.edad = edad;
		}

		public Tipos getTipo() {
			return tipo;
		}

		public void setTipo(Tipos tipo) {
			this.tipo = tipo;
		}

		public Estados getEstado() {
			return estado;
		}

		public void setEstado(Estados estado) {
			this.estado = estado;
		}


		

		
}
