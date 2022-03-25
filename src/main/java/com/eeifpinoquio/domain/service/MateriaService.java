package com.eeifpinoquio.domain.service;

import static org.apache.commons.lang3.ObjectUtils.notEqual;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeifpinoquio.domain.exception.PinoquioException;
import com.eeifpinoquio.domain.exception.RecursoEmUsoException;
import com.eeifpinoquio.domain.exception.RecursoJaExisteException;
import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Materia;
import com.eeifpinoquio.domain.model.Professor;
import com.eeifpinoquio.domain.repository.MateriaRepository;

@Service
public class MateriaService {
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	@Autowired
	private ProfessorService professorService;
	
	private static final String RECURSO_MATERIA = "Materia";
	
	
	@Transactional
	public Materia salvar(Materia materia) {	
		
		Optional<Materia> materiaExistente = materiaRepository.findByDescricao(materia.getDescricao());
		
		if(materiaExistente.isPresent()) {
			throw new RecursoJaExisteException(RECURSO_MATERIA);
		}
		
		Long professorId = materia.getProfessor().getId();		
		Professor professorExistente = professorService.buscarOuFalhar(professorId);
		
		materia.setProfessor(professorExistente);
		
		return materiaRepository.save(materia);
	}
	
	@Transactional
	public Materia alterar(Long id, Materia materiaAtualizado) {	
		
		Materia materiaAtual = buscarOuFalhar(id);
		
		Optional<Materia> materiaExistente = materiaRepository.findByDescricao(materiaAtualizado.getDescricao());
		
		if(materiaExistente.isPresent() && notEqual(materiaAtual, materiaExistente.get())) {
			throw new PinoquioException(RECURSO_MATERIA);
		}
		
		Long professorId = materiaAtualizado.getProfessor().getId();		
		Professor professorExistente = professorService.buscarOuFalhar(professorId);		
		
		materiaAtual.setTitulo(materiaAtualizado.getTitulo());
		materiaAtual.setDescricao(materiaAtualizado.getDescricao());
		materiaAtual.setProfessor(professorExistente);
		
		return materiaRepository.save(materiaAtual);
	}
	
	@Transactional
	public void excluir(Long id) {
		
		try {
			materiaRepository.deleteById(id);
			materiaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException(RECURSO_MATERIA, id);
		
		} catch (DataIntegrityViolationException e) {
			throw new RecursoEmUsoException(RECURSO_MATERIA);
		}
	}
	
	public List<Materia> listarTodos() { 
		return materiaRepository.findAll();
	}
	
	public Materia buscarOuFalhar(Long id) {
		return materiaRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_MATERIA, id));
	}

}
