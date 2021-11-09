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

import com.eeifpinoquio.api.controller.converter.serie.SerieMapper;
import com.eeifpinoquio.api.controller.converter.serie.SerieRequest;
import com.eeifpinoquio.api.controller.converter.serie.SerieResponse;
import com.eeifpinoquio.api.controller.util.ResponseHeaderUtil;
import com.eeifpinoquio.domain.model.Serie;
import com.eeifpinoquio.domain.service.SerieService;

@RestController
@RequestMapping(value = "/series", produces = MediaType.APPLICATION_JSON_VALUE)
public class SerieController {
	
	@Autowired
	private SerieService serieService;

	@Autowired
	private SerieMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SerieResponse adicionar(@RequestBody @Valid SerieRequest serieRequest) {

		Serie serie = mapper.convert(serieRequest);

		SerieResponse serieResponse = mapper.convert(serieService.salvar(serie));

		ResponseHeaderUtil.adicionarLocation(serieResponse.getId());

		return serieResponse;
	}
	
	@PutMapping("/{id}")
	public SerieResponse atualizar(@PathVariable Long id, @RequestBody @Valid SerieRequest serieRequest) {

		Serie serieAtualizado = mapper.convert(serieRequest);

		return mapper.convert(serieService.alterar(id, serieAtualizado));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		serieService.excluir(id);	
	}

	@GetMapping
	public List<SerieResponse> listar() {
		return mapper.convert(serieService.listarTodos());
	}

	@GetMapping("/{id}")
	public SerieResponse buscar(@PathVariable Long id) {
		return mapper.convert(serieService.buscarOuFalhar(id));
	}

}
