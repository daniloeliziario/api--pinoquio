package com.eeifpinoquio.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeifpinoquio.domain.exception.RecursoEmUsoException;
import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Disciplina;
import com.eeifpinoquio.domain.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	private static final String RECURSO_DISCIPLINA = "Disciplina";
	
	
	@Transactional
	public Disciplina salvar(Disciplina disciplina) {			
		return disciplinaRepository.save(disciplina);
	}
	
	@Transactional
	public Disciplina alterar(Long id, Disciplina disciplinaAtualizado) {	
		
		Disciplina disciplinaAtual = buscarOuFalhar(id);
		
		disciplinaAtual.setNome(disciplinaAtualizado.getNome());
		
		return disciplinaRepository.save(disciplinaAtual);
	}
	
	@Transactional
	public void excluir(Long id) {
		
		try {
			disciplinaRepository.deleteById(id);
			disciplinaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException(RECURSO_DISCIPLINA, id);
		
		} catch (DataIntegrityViolationException e) {
			throw new RecursoEmUsoException(RECURSO_DISCIPLINA);
		}
	}
	
	public List<Disciplina> listarTodos() { 
		return disciplinaRepository.findAll();
	}
	
	public Disciplina buscarOuFalhar(Long id) {
		return disciplinaRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_DISCIPLINA, id));
	}

}
