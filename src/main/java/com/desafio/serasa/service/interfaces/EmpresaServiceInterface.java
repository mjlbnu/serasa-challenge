package com.desafio.serasa.service.interfaces;

import java.util.List;

import com.desafio.serasa.entity.Empresa;

public interface EmpresaServiceInterface {
	
	List<Empresa> listar();
	
	void reCalcularNotaEmpresa(Long id);

}
