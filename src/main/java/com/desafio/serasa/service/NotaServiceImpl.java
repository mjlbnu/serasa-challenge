package com.desafio.serasa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.serasa.entity.Nota;
import com.desafio.serasa.repository.NotaRepository;

@Service
public class NotaServiceImpl implements NotaServiceInterface{
	
	@Autowired
	private NotaRepository notaRepository;

	@Override
	public void gravarNotas(List<Nota> notas) {
		notaRepository.saveAll(notas);
	}

}
