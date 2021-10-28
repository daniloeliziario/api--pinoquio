package com.eeifpinoquio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eeifpinoquio.domain.model.Noticia;
import com.eeifpinoquio.domain.service.NoticiaService;

@RestController
@RequestMapping(value = "/noticias", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoticiaController {
	
	@Autowired
	private NoticiaService noticiaService;

	@GetMapping
	public List<Noticia> listar() {
		return noticiaService.listarTodos();
	}

	@GetMapping("/{id}")
	public Noticia buscar(@PathVariable Long id) {
		return noticiaService.buscarOuFalhar(id);
	}

}
