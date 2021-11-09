package com.eeifpinoquio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eeifpinoquio.api.controller.converter.disciplina.DisciplinaMapper;
import com.eeifpinoquio.api.controller.converter.disciplina.DisciplinaRequest;
import com.eeifpinoquio.api.controller.converter.disciplina.DisciplinaResponse;
import com.eeifpinoquio.api.controller.util.ResponseHeaderUtil;
import com.eeifpinoquio.domain.model.Disciplina;
import com.eeifpinoquio.domain.service.DisciplinaService;

@RestController
@RequestMapping(value = "/disciplinas", produces = MediaType.APPLICATION_JSON_VALUE)
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService disciplinaService;

	@Autowired
	private DisciplinaMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DisciplinaResponse adicionar(@RequestBody @Valid DisciplinaRequest disciplinaRequest) {

		Disciplina disciplina = mapper.convert(disciplinaRequest);

		DisciplinaResponse disciplinaResponse = mapper.convert(disciplinaService.salvar(disciplina));

		ResponseHeaderUtil.adicionarLocation(disciplinaResponse.getId());

		return disciplinaResponse;
	}
	
	@PutMapping("/{id}")
	public DisciplinaResponse atualizar(@PathVariable Long id, @RequestBody @Valid DisciplinaRequest disciplinaRequest) {

		Disciplina disciplinaAtualizado = mapper.convert(disciplinaRequest);

		return mapper.convert(disciplinaService.alterar(id, disciplinaAtualizado));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		disciplinaService.excluir(id);	
	}

	@GetMapping
	public List<DisciplinaResponse> listar() {
		return mapper.convert(disciplinaService.listarTodos());
	}

	@GetMapping("/{id}")
	public DisciplinaResponse buscar(@PathVariable Long id) {
		return mapper.convert(disciplinaService.buscarOuFalhar(id));
	}
	
}
