package com.desafio.serasa.service;

import java.util.List;

import com.desafio.serasa.entity.Nota;

public interface NotaServiceInterface {
	
	Integer atualizarRankingEmpresa(Long id_empresa);
	
	void gravarNotas(List<Nota> notas);

}
