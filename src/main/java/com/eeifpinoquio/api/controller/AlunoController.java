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

import com.eeifpinoquio.api.controller.converter.aluno.AlunoMapper;
import com.eeifpinoquio.api.controller.converter.aluno.AlunoRequest;
import com.eeifpinoquio.api.controller.converter.aluno.AlunoResponse;
import com.eeifpinoquio.api.controller.util.ResponseHeaderUtil;
import com.eeifpinoquio.domain.model.Aluno;
import com.eeifpinoquio.domain.service.AlunoService;

@RestController
@RequestMapping(value = "/alunos", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;

	@Autowired
	private AlunoMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlunoResponse adicionar(@RequestBody @Valid AlunoRequest alunoRequest) {

		Aluno aluno = mapper.convert(alunoRequest);

		AlunoResponse alunoResponse = mapper.convert(alunoService.salvar(aluno));

		ResponseHeaderUtil.adicionarLocation(alunoResponse.getMatricula());

		return alunoResponse;
	}

	@PutMapping("/{matricula}")
	public AlunoResponse atualizar(@PathVariable Long matricula, @RequestBody @Valid AlunoRequest alunoRequest) {

		Aluno alunoAtualizado = mapper.convert(alunoRequest);

		return mapper.convert(alunoService.alterar(matricula, alunoAtualizado));
	}
	
	@DeleteMapping("/{matricula}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long matricula) {
		alunoService.excluir(matricula);	
	}
	
	@PutMapping("/{matricula}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long matricula) {
		alunoService.ativar(matricula);
	}
	
	@DeleteMapping("/{matricula}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long matricula) {
		alunoService.inativar(matricula);
	}

	@GetMapping
	public List<AlunoResponse> listar() {
		return mapper.convert(alunoService.listarTodos());
	}

	@GetMapping("/{matricula}")
	public AlunoResponse buscar(@PathVariable Long matricula) {
		return mapper.convert(alunoService.buscarOuFalhar(matricula));
	}

}
