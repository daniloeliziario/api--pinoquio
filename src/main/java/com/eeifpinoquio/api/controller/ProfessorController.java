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

import com.eeifpinoquio.api.controller.converter.professor.ProfessorMapper;
import com.eeifpinoquio.api.controller.converter.professor.ProfessorRequest;
import com.eeifpinoquio.api.controller.converter.professor.ProfessorResponse;
import com.eeifpinoquio.api.controller.util.ResponseHeaderUtil;
import com.eeifpinoquio.domain.model.Professor;
import com.eeifpinoquio.domain.service.ProfessorService;

@RestController
@RequestMapping(value = "/professores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;

	@Autowired
	private ProfessorMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProfessorResponse adicionar(@RequestBody @Valid ProfessorRequest professorRequest) {

		Professor professor = mapper.convert(professorRequest);

		ProfessorResponse professorResponse = mapper.convert(professorService.salvar(professor));

		ResponseHeaderUtil.adicionarLocation(professorResponse.getId());

		return professorResponse;
	}

	@PutMapping("/{id}")
	public ProfessorResponse atualizar(@PathVariable Long id, @RequestBody @Valid ProfessorRequest professorRequest) {

		Professor professorAtualizado = mapper.convert(professorRequest);

		return mapper.convert(professorService.alterar(id, professorAtualizado));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		professorService.excluir(id);	
	}

	@GetMapping
	public List<ProfessorResponse> listar() {
		return mapper.convert(professorService.listarTodos());
	}

	@GetMapping("/{id}")
	public ProfessorResponse buscar(@PathVariable Long id) {
		return mapper.convert(professorService.buscarOuFalhar(id));
	}

}
