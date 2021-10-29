package com.eeifpinoquio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eeifpinoquio.api.controller.converter.noticia.NoticiaMapper;
import com.eeifpinoquio.api.controller.converter.noticia.NoticiaResponse;

import com.eeifpinoquio.domain.service.NoticiaService;

@RestController
@RequestMapping(value = "/noticias", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoticiaController {
	
	@Autowired
	private NoticiaService noticiaService;
	
	@Autowired
	private NoticiaMapper mapper;

	@GetMapping
	public List<NoticiaResponse> listar() {
		return mapper.convert(noticiaService.listarTodos());
	}

	@GetMapping("/{id}")
	public NoticiaResponse buscar(@PathVariable Long id) {
		return mapper.convert(noticiaService.buscarOuFalhar(id));
	}

}
