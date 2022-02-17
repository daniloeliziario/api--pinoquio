package com.eeifpinoquio.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeifpinoquio.domain.exception.RecursoEmUsoException;
import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Materia;
import com.eeifpinoquio.domain.repository.MateriaRepository;

@Service
public class MateriaService {
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	private static final String RECURSO_MATERIA = "Materia";
	
	
	@Transactional
	public Materia salvar(Materia materia) {			
		return materiaRepository.save(materia);
	}
	
	@Transactional
	public Materia alterar(Long id, Materia materiaAtualizado) {	
		
		Materia materiaAtual = buscarOuFalhar(id);
		
		materiaAtual.setNome(materiaAtualizado.getNome());
		
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
