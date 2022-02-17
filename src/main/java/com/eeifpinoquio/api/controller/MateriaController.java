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

import com.eeifpinoquio.api.controller.converter.materia.MateriaMapper;
import com.eeifpinoquio.api.controller.converter.materia.MateriaRequest;
import com.eeifpinoquio.api.controller.converter.materia.MateriaResponse;
import com.eeifpinoquio.api.controller.util.ResponseHeaderUtil;
import com.eeifpinoquio.domain.model.Materia;
import com.eeifpinoquio.domain.service.MateriaService;

@RestController
@RequestMapping(value = "/materias", produces = MediaType.APPLICATION_JSON_VALUE)
public class MateriaController {
	
	@Autowired
	private MateriaService materiaService;

	@Autowired
	private MateriaMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MateriaResponse adicionar(@RequestBody @Valid MateriaRequest materiaRequest) {

		Materia materia = mapper.convert(materiaRequest);

		MateriaResponse materiaResponse = mapper.convert(materiaService.salvar(materia));

		ResponseHeaderUtil.adicionarLocation(materiaResponse.getId());

		return materiaResponse;
	}
	
	@PutMapping("/{id}")
	public MateriaResponse atualizar(@PathVariable Long id, @RequestBody @Valid MateriaRequest materiaRequest) {

		Materia materiaAtualizado = mapper.convert(materiaRequest);

		return mapper.convert(materiaService.alterar(id, materiaAtualizado));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		materiaService.excluir(id);	
	}

	@GetMapping
	public List<MateriaResponse> listar() {
		return mapper.convert(materiaService.listarTodos());
	}

	@GetMapping("/{id}")
	public MateriaResponse buscar(@PathVariable Long id) {
		return mapper.convert(materiaService.buscarOuFalhar(id));
	}
	
}
