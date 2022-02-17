package com.eeifpinoquio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eeifpinoquio.api.controller.converter.alunoBoletim.AlunoBoletimMapper;
import com.eeifpinoquio.api.controller.converter.alunoBoletim.AlunoBoletimResponse;
import com.eeifpinoquio.domain.service.AlunoBoletimService;

@RestController
@RequestMapping(value = "/alunos/{alunosId}/boletim", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlunoBoletimController {
	
	@Autowired
	private AlunoBoletimService alunoBoletimService;
	
	@Autowired
	private AlunoBoletimMapper mapper;
	
	@GetMapping("/{boletimId}")
	public AlunoBoletimResponse recuperarBoletimAluno(@PathVariable Long alunoId, @PathVariable Long boletimId) {
		return mapper.convert(alunoBoletimService.buscarBoletim(alunoId, boletimId));
	}

}
