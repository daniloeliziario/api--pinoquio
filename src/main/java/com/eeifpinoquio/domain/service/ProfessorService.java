package com.eeifpinoquio.domain.service;

import static org.apache.commons.lang3.ObjectUtils.notEqual;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeifpinoquio.domain.exception.RecursoEmUsoException;
import com.eeifpinoquio.domain.exception.RecursoJaExisteException;
import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Professor;
import com.eeifpinoquio.domain.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	private static final String RECURSO_PROFESSOR = "Professor";
	
	private static final String CAMPO_UNICO = "nome";	
	
	@Transactional
	public Professor salvar(Professor professor) {		
		
		Optional<Professor> professorExistente = professorRepository.findByNome(professor.getNome());
		
		if(professorExistente.isPresent()) {
			throw new RecursoJaExisteException(RECURSO_PROFESSOR, CAMPO_UNICO);
		}
		
		return professorRepository.save(professor);
	}
	
	@Transactional
	public Professor alterar(Long id, Professor professorAtualizado) {	
		
		Professor professorAtual = buscarOuFalhar(id);
		
		Optional<Professor> professorExistente = professorRepository.findByNome(professorAtualizado.getNome());
		
		if(professorExistente.isPresent() && notEqual(professorAtual, professorExistente.get())) {
			throw new RecursoJaExisteException(RECURSO_PROFESSOR, CAMPO_UNICO);
		}
		
		professorAtual.setNome(professorAtualizado.getNome());
		
		return professorRepository.save(professorAtual);
	}
	
	@Transactional
	public void excluir(Long id) {
		
		try {
			professorRepository.deleteById(id);
			professorRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException(RECURSO_PROFESSOR, id);
		
		} catch (DataIntegrityViolationException e) {
			throw new RecursoEmUsoException(RECURSO_PROFESSOR, id);
		}
	}
	
	public List<Professor> listarTodos() { 
		return professorRepository.findAll();
	}
	
	public Professor buscarOuFalhar(Long id) {
		return professorRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_PROFESSOR, id));
	}

}
