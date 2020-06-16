package com.desafio.serasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desafio.serasa.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	

}
