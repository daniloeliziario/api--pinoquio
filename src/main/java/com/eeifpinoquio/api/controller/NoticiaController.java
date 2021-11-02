package com.eeifpinoquio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eeifpinoquio.api.controller.converter.noticia.NoticiaMapper;
import com.eeifpinoquio.api.controller.converter.noticia.NoticiaRequest;
import com.eeifpinoquio.api.controller.converter.noticia.NoticiaResponse;
import com.eeifpinoquio.api.controller.util.ResponseHeaderUtil;
import com.eeifpinoquio.domain.model.Noticia;
import com.eeifpinoquio.domain.service.NoticiaService;

@RestController
@RequestMapping(value = "/noticias", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoticiaController {
	
	@Autowired
	private NoticiaService noticiaService;
	
	@Autowired
	private NoticiaMapper mapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public NoticiaResponse adicionar(@RequestBody @Valid NoticiaRequest noticiaRequest) {

		Noticia noticia = mapper.convert(noticiaRequest);

		NoticiaResponse noticiaResponse = mapper.convert(noticiaService.salvar(noticia));

		ResponseHeaderUtil.adicionarLocation(noticiaResponse.getId());

		return noticiaResponse;
	}

	@PutMapping("/{id}")
	public NoticiaResponse atualizar(@PathVariable Long id, @RequestBody @Valid NoticiaRequest noticiaRequest) {

		Noticia noticiaAtualizado = mapper.convert(noticiaRequest);

		return mapper.convert(noticiaService.alterar(id, noticiaAtualizado));
	}

	@GetMapping
	public List<NoticiaResponse> listar() {
		return mapper.convert(noticiaService.listarTodos());
	}

	@GetMapping("/{id}")
	public NoticiaResponse buscar(@PathVariable Long id) {
		return mapper.convert(noticiaService.buscarOuFalhar(id));
	}

}
