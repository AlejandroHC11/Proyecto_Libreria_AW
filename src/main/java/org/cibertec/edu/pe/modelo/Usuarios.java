package org.cibertec.edu.pe.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
	@Table(name = "Usuarios")
	public class Usuarios {

	// Campos o atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	    @Column(name = "tipo", columnDefinition = "int default 2")
	    private int tipo;

	    @Column(name = "estado", columnDefinition = "int default 1")
	    private int estados;

	    @ManyToOne
	    @JoinColumn(name = "tipo", referencedColumnName = "idtipo", insertable = false, updatable = false)
	    private Tipos tipos;

	    @ManyToOne
	    @JoinColumn(name = "estado", referencedColumnName = "idestado", insertable = false, updatable = false)
	    private Estados estado;

	    
	 // Métodos Constructores
		public Usuarios(int codigo, String nombre, String apellido, String usuario, String clave, int edad, int tipo,
				int estados, Tipos tipos, Estados estado) {
			super();
			this.codigo = codigo;
			this.nombre = nombre;
			this.apellido = apellido;
			this.usuario = usuario;
			this.clave = clave;
			this.edad = edad;
			this.tipo = tipo;
			this.estados = estados;
			this.tipos = tipos;
			this.estado = estado;
		}
		
		
		public Usuarios() {
			super();
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

		public int getTipo() {
			return tipo;
		}

		public void setTipo(int tipo) {
			this.tipo = tipo;
		}

		public int getEstados() {
			return estados;
		}

		public void setEstados(int estados) {
			this.estados = estados;
		}

		public Tipos getTipos() {
			return tipos;
		}

		public void setTipos(Tipos tipos) {
			this.tipos = tipos;
		}

		public Estados getEstado() {
			return estado;
		}

		public void setEstado(Estados estado) {
			this.estado = estado;
		}
		
		
	    
	    

}
