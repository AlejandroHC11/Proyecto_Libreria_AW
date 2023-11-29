package org.cibertec.edu.pe.repository;

import java.util.List;

import org.cibertec.edu.pe.modelo.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICategoriasRepository extends JpaRepository<Categorias, Integer>{
	List<Categorias> findByDescripcion(String descripcion);
	@Query("SELECT MAX(c.idcategoria) FROM Categorias c")
    Long ultimoId();
}
