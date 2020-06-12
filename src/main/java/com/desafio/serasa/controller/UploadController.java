package com.desafio.serasa.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.desafio.serasa.entity.Empresa;
import com.desafio.serasa.entity.Nota;
import com.desafio.serasa.service.NotaServiceImpl;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Controller
public class UploadController {
	
	@Autowired
	NotaServiceImpl notaService;

	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("empresa", new Empresa());
		
		return "index";
	}
	
	@PostMapping("/upload-notas")
	public void UploadNotas(@RequestParam("file") MultipartFile file, Model model, @ModelAttribute Empresa empresa) {

		// validate file
		if (file.isEmpty()) {
			model.addAttribute("message", "Selecione um arquivo CSV para upload");
			model.addAttribute("status", false);			
		} else {

			// parse CSV file to create a list of `Operacoes` objects
			try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

				// create csv bean reader
				@SuppressWarnings({ "unchecked", "rawtypes" })
				CsvToBean<Nota> csvToBean = new CsvToBeanBuilder(reader).withType(Nota.class)
						.withIgnoreLeadingWhiteSpace(true).build();

				// convert `CsvToBean` object to list of notas
				List<Nota> notas = csvToBean.parse();
				
				for (Nota nota : notas) {
					nota.setId_empresa(empresa.getId());
				}

				// save notas on db
				notaService.gravarNotas(notas);

				// save notas on list on model
				model.addAttribute("notas", notas);
				model.addAttribute("status", true);

			} catch (Exception e) {
				model.addAttribute("message", "Ocorreu um erro durante o processamento do arquivo");
				model.addAttribute("status", false);
			}
		}
	}

}
