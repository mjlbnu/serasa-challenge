package com.desafio.serasa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.desafio.serasa.entity.Empresa;
import com.desafio.serasa.repository.EmpresaRepository;

@Service
public class EmpresaServiceImpl implements EmpresaServiceInterface {
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public List<Empresa> listar() {
		return empresaRepository.findAll(Sort.by(Sort.Direction.DESC, "pontuacao"));
	}

	@Override
	public void calcularNotaEmpresa(Long id) {
		// TODO Auto-generated method stub
		
	}

}
