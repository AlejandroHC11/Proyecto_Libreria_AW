package org.cibertec.edu.pe.repository;

import java.util.List;

import org.cibertec.edu.pe.modelo.Tipos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITiposRepository extends JpaRepository<Tipos, Integer>{
	List<Tipos> findByDescripcion(String descripcion);
	@Query("SELECT MAX(t.idtipo) FROM Tipos t")
    Long ultimoId();
}
