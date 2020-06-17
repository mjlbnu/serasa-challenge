package com.desafio.serasa.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
		
		final BigDecimal PERCENTUAL_FISCAL = new BigDecimal("0.02");
		final BigDecimal PERCENTUAL_DEBITO = new BigDecimal("0.04");
		
		// Busca a pontuação atual da empresa
		Empresa empresa = empresaRepository.findById(id).get();
		BigDecimal pontuacaoAtual = empresa.getPontuacao();
		
		// Busca as notas PENDENTES
		List<Nota> notasFiscais = notaRepository.buscarNotasPendentesPorTipo(id, "FISCAL", "PENDENTE");
		List<Nota> notasDebito = notaRepository.buscarNotasPendentesPorTipo(id, "DEBITO", "PENDENTE");
		
		pontuacaoAtual = processaNotas(notasFiscais, RoundingMode.HALF_DOWN,
				PERCENTUAL_FISCAL, pontuacaoAtual, "FISCAL");
		
		pontuacaoAtual = processaNotas(notasDebito, RoundingMode.HALF_UP,
				PERCENTUAL_DEBITO, pontuacaoAtual, "DEBITO");
		
		pontuacaoAtual = verificaLimitesPontuacao(pontuacaoAtual);
		
		// salva a nova pontuação da empresa
		empresa.setPontuacao(pontuacaoAtual);
		empresaRepository.save(empresa);
	}

	private BigDecimal verificaLimitesPontuacao(BigDecimal pontos) {
		final BigDecimal LIMITE_SUPERIOR = new BigDecimal("100");
		final BigDecimal LIMITE_INFERIOR = new BigDecimal("1");
		
		// > 100
		if (pontos.compareTo(LIMITE_SUPERIOR) == 1) {
			pontos = LIMITE_SUPERIOR;
		}

		// < 1
		if (pontos.compareTo(LIMITE_INFERIOR) == -1) {
			pontos = LIMITE_INFERIOR;
		}
		
		return pontos;
	}
	
	private BigDecimal processaNotas(List<Nota> notas, RoundingMode roundingMode,
			BigDecimal percentual, BigDecimal pontuacaoAtual, String tipoNota) {
		for (Nota nota : notas) {
			BigDecimal valorPercentual = pontuacaoAtual.multiply(percentual);
				
			BigDecimal valorOperacao = tipoNota.equalsIgnoreCase("FISCAL") 
					? pontuacaoAtual.add(valorPercentual)
					: pontuacaoAtual.subtract(valorPercentual);
			
			BigDecimal arred = valorOperacao.round(new MathContext(4, roundingMode));
			pontuacaoAtual = arred;
			
			nota.setStatus("PROCESSADA");
			notaRepository.save(nota);
		}
		return pontuacaoAtual;
	}
	
	
}
