package com.desafio.serasa.service.interfaces;

import java.util.List;

import com.desafio.serasa.entity.Nota;

public interface NotaServiceInterface {
	
	void gravarNotas(List<Nota> notas);
	
	List<Nota> listarPendentes(Long id, String tipoNota, String status);

}
