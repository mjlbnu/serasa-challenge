package com.desafio.serasa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafio.serasa.entity.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long>{
	
	@Query(value = "select * from notas where id_empresa = :idEmpresa " + 
			"and tp_nota = :tipoNota and status = :status", nativeQuery = true)
	List<Nota> buscarNotasPendentesPorTipo(
			@Param("idEmpresa") Long id,
			@Param("tipoNota") String tipoNota,
			@Param("status") String status);

}
