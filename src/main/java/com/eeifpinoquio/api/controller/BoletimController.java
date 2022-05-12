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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eeifpinoquio.api.controller.converter.boletim.BoletimMapper;
import com.eeifpinoquio.api.controller.converter.boletim.BoletimRequest;
import com.eeifpinoquio.api.controller.converter.boletim.BoletimResponse;
import com.eeifpinoquio.api.controller.util.ResponseHeaderUtil;
import com.eeifpinoquio.domain.model.Boletim;
import com.eeifpinoquio.domain.service.BoletimService;

@RestController
@RequestMapping(value = "/boletins", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoletimController {
	
	@Autowired
	private BoletimService boletimService;

	@Autowired
	private BoletimMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BoletimResponse adicionar(@RequestBody @Valid BoletimRequest boletimRequest) {

		Boletim boletim = mapper.convert(boletimRequest);

		BoletimResponse boletimResponse = mapper.convert(boletimService.salvar(boletim));

		ResponseHeaderUtil.adicionarLocation(boletimResponse.getId());

		return boletimResponse;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		boletimService.excluir(id);	
	}

	@GetMapping
	public List<BoletimResponse> listar() {
		return mapper.convert(boletimService.listarTodos());
	}

	@GetMapping("/{id}")
	public BoletimResponse buscar(@PathVariable Long id) {
		return mapper.convert(boletimService.buscarOuFalhar(id));
	}
	
	/*
	 * @GetMapping public List<BoletimResponse> buscarPorAluno(@RequestParam String
	 * aluno) { return mapper.convert(boletimService.buscarBoletimAluno(aluno)); }
	 */

}
