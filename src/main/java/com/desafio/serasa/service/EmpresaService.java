package com.desafio.serasa.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.desafio.serasa.entity.Empresa;
import com.desafio.serasa.entity.Nota;
import com.desafio.serasa.repository.EmpresaRepository;
import com.desafio.serasa.repository.NotaRepository;
import com.desafio.serasa.service.interfaces.EmpresaServiceInterface;

@Service
public class EmpresaService implements EmpresaServiceInterface {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired 
	private NotaRepository notaRepository;

	@Override
	public List<Empresa> listar() {
		return empresaRepository.findAll(Sort.by(Sort.Direction.DESC, "pontuacao"));
	}

	@Override
	public void reCalcularNotaEmpresa(Long id) {
		
		BigDecimal pcFISCAL = new BigDecimal("0.02");
		BigDecimal pcDEBITO = new BigDecimal("0.04");
		
		// busca a pontuação atual da empresa
		Empresa empresa = empresaRepository.findById(id).get();
		BigDecimal pontuacaoAtual = empresa.getPontuacao();
		
		// seleciona as notas PENDENTES
		List<Nota> notasFiscais = notaRepository.buscarNotasPendentesPorTipo(id, "FISCAL", "PENDENTE");
		List<Nota> notasDebito = notaRepository.buscarNotasPendentesPorTipo(id, "DEBITO", "PENDENTE");
		
		for (Nota nota : notasFiscais) {
			pontuacaoAtual = pontuacaoAtual.add(pontuacaoAtual.multiply(pcFISCAL).setScale(2));
			pontuacaoAtual = pontuacaoAtual.setScale(0, BigDecimal.ROUND_DOWN);
			System.out.println(pontuacaoAtual);
		}
		
		/*
		System.out.println("subtract:");
		// diminui uma de débito
		pontuacaoAtual = pontuacaoAtual.subtract(pontuacaoAtual.multiply(pcDEBITO).setScale(2));
		pontuacaoAtual = pontuacaoAtual.setScale(0, BigDecimal.ROUND_UP);
		System.out.println(pontuacaoAtual);
		*/
		
		for (Nota nota : notasDebito) {
			pontuacaoAtual = pontuacaoAtual.subtract(pontuacaoAtual.multiply(pcDEBITO).setScale(2));
			pontuacaoAtual = pontuacaoAtual.setScale(0, BigDecimal.ROUND_UP);
			System.out.println(pontuacaoAtual);
		}
		
		BigDecimal limiteSuperior = new BigDecimal("100");
		BigDecimal limiteInferior = new BigDecimal("1");
		
		// > 100
		if(pontuacaoAtual.compareTo(limiteSuperior) == 1) {
			pontuacaoAtual = limiteSuperior;
		}
		
		// < 1
		if(pontuacaoAtual.compareTo(limiteInferior) == -1) {
			pontuacaoAtual = limiteInferior;
		}
		
		System.out.println("--------------");
		System.out.println(empresa.getNome() + ": " + pontuacaoAtual);
		
		// salva a nova pontuação da empresa
		
		// redireciona para o ranking
		
	}

}
