package com.desafio.serasa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.desafio.serasa.entity.Empresa;
import com.desafio.serasa.service.EmpresaServiceImpl;


@Controller
public class RankingController {
	
	@Autowired
	EmpresaServiceImpl empresaService;

	
	@GetMapping("/ranking")
	public void CarregaRanking(Model model) {
		
		try {
			List<Empresa> empresas =  empresaService.listar();
			
			model.addAttribute("empresas", empresas);
			model.addAttribute("status", true);
		} catch (Exception e) {
			model.addAttribute("message", "Ocorreu um erro durante o carregamento do ranking");
			model.addAttribute("status", false);
		}
	}
	

}
