package com.eeifpinoquio.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeifpinoquio.domain.exception.RecursoEmUsoException;
import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Serie;
import com.eeifpinoquio.domain.repository.SerieRepository;

@Service
public class SerieService {
	
	@Autowired
	private SerieRepository serieRepository;
	
	private static final String RECURSO_SERIE = "Série";
	
	
	@Transactional
	public Serie salvar(Serie serie) {			
		return serieRepository.save(serie);
	}
	
	@Transactional
	public Serie alterar(Long id, Serie serieAtualizado) {	
		
		Serie serieAtual = buscarOuFalhar(id);
		
		serieAtual.setNome(serieAtualizado.getNome());
		
		return serieRepository.save(serieAtual);
	}
	
	@Transactional
	public void excluir(Long id) {
		
		try {
			serieRepository.deleteById(id);
			serieRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException(RECURSO_SERIE, id);
		
		} catch (DataIntegrityViolationException e) {
			throw new RecursoEmUsoException(RECURSO_SERIE);
		}
	}
	
	public List<Serie> listarTodos() { 
		return serieRepository.findAll();
	}
	
	public Serie buscarOuFalhar(Long id) {
		return serieRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_SERIE, id));
	}

}
