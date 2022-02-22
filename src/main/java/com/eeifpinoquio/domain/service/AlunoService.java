package com.eeifpinoquio.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeifpinoquio.domain.exception.RecursoEmUsoException;
import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Aluno;
import com.eeifpinoquio.domain.model.Boletim;
import com.eeifpinoquio.domain.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	private static final String RECURSO_ALUNO = "Aluno";
	
	@Transactional
	public Aluno salvar(Aluno aluno) {			
		return alunoRepository.save(aluno);
	}
	
	@Transactional
	public Aluno alterar(Long id, Aluno alunoAtualizado) {	
		
		Aluno alunoAtual = buscarOuFalhar(id);
		
		alunoAtual.setNome(alunoAtualizado.getNome());
		
		return alunoRepository.save(alunoAtual);
	}
	
	@Transactional
	public void excluir(Long id) {
		
		try {
			alunoRepository.deleteById(id);
			alunoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException(RECURSO_ALUNO, id);
		
		} catch (DataIntegrityViolationException e) {
			throw new RecursoEmUsoException(RECURSO_ALUNO);
		}
	}
	
	public List<Aluno> listarTodos() { 
		return alunoRepository.findAll();
	}
	
	public Aluno buscarOuFalhar(Long id) {
		return alunoRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_ALUNO, id));
	}
	
	public Boletim buscarBoletim(Long alunoId) { 
		
		Aluno aluno = alunoRepository.findById(alunoId)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_ALUNO, alunoId));
		
		return aluno.getBoletins().get(0);
	}

}
