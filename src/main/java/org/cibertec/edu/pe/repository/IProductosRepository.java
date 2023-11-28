package org.cibertec.edu.pe.repository;

import java.util.List;

import org.cibertec.edu.pe.modelo.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductosRepository extends JpaRepository<Productos, Integer>	 {
	List<Productos> findByDescripcion(String descripcion);
}
