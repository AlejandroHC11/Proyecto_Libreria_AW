package org.cibertec.edu.pe.repository;

import java.util.List;

import org.cibertec.edu.pe.modelo.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductosRepository extends JpaRepository<Productos, Integer>	 {
	List<Productos> findByDescripcion(String descripcion);
	@Query("SELECT MAX(p.idprod) FROM Productos p")
    Long ultimoId();
	@Query("SELECT p FROM Productos p WHERE p.categoria.idcategoria = :idcategoria")
	List<Productos> findByIdCategoria(int idcategoria);
}
