package org.cibertec.edu.pe.repository;

import java.util.List;

import org.cibertec.edu.pe.modelo.Estados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEstadosRepository extends JpaRepository<Estados, Integer>{
	List<Estados> findByDescripcion(String descripcion);
	@Query("SELECT MAX(e.idestado) FROM Estados e")
    Long ultimoId();
}
