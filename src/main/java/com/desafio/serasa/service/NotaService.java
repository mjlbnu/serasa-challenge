package com.desafio.serasa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.serasa.entity.Nota;
import com.desafio.serasa.repository.NotaRepository;
import com.desafio.serasa.service.interfaces.NotaServiceInterface;

@Service
public class NotaService implements NotaServiceInterface{
	
	@Autowired
	private NotaRepository notaRepository;

	@Override
	public void gravarNotas(List<Nota> notas) {
		notaRepository.saveAll(notas);
	}

	@Override
	public List<Nota> listarPendentes(Long id, String tipoNota, String status) {
		return notaRepository.buscarNotasPendentesPorTipo(id, tipoNota, status);
	}

}
