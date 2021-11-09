package com.eeifpinoquio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Aluno;
import com.eeifpinoquio.domain.model.Boletim;
import com.eeifpinoquio.domain.repository.AlunoRepository;

@Service
public class AlunoBoletimService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	private static final String RECURSO_ALUNO = "Aluno";
	
	public Boletim buscarBoletim(Long alunoId) { 
		
		Aluno aluno = alunoRepository.findById(alunoId)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_ALUNO, alunoId));
		
		return aluno.getBoletim();
	}

}
