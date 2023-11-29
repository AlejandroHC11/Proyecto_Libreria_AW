package org.cibertec.edu.pe.repository;

import java.util.List;

import org.cibertec.edu.pe.modelo.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuariosRepository extends JpaRepository<Usuarios, Integer>{
	List<Usuarios> findByUsuario(String usuario);
	@Query("SELECT MAX(u.codigo) FROM Usuarios u")
    Long ultimoId();
}
