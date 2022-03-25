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

import com.eeifpinoquio.api.controller.converter.ano.AnoMapper;
import com.eeifpinoquio.api.controller.converter.ano.AnoRequest;
import com.eeifpinoquio.api.controller.converter.ano.AnoResponse;
import com.eeifpinoquio.api.controller.util.ResponseHeaderUtil;
import com.eeifpinoquio.domain.model.Ano;
import com.eeifpinoquio.domain.service.AnoService;

@RestController
@RequestMapping(value = "/anos", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnoController {
	
	@Autowired
	private AnoService anoService;

	@Autowired
	private AnoMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AnoResponse adicionar(@RequestBody @Valid AnoRequest anoRequest) {

		Ano ano = mapper.convert(anoRequest);

		AnoResponse anoResponse = mapper.convert(anoService.salvar(ano));

		ResponseHeaderUtil.adicionarLocation(anoResponse.getId());

		return anoResponse;
	}
	
	@PutMapping("/{id}")
	public AnoResponse atualizar(@PathVariable Long id, @RequestBody @Valid AnoRequest anoRequest) {

		Ano anoAtualizado = mapper.convert(anoRequest);

		return mapper.convert(anoService.alterar(id, anoAtualizado));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		anoService.excluir(id);	
	}

	@GetMapping
	public List<AnoResponse> listar() {
		return mapper.convert(anoService.listarTodos());
	}

	@GetMapping("/{id}")
	public AnoResponse buscar(@PathVariable Long id) {
		return mapper.convert(anoService.buscarOuFalhar(id));
	}

}
