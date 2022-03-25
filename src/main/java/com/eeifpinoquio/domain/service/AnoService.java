package com.eeifpinoquio.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeifpinoquio.domain.exception.RecursoEmUsoException;
import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Ano;
import com.eeifpinoquio.domain.repository.AnoRepository;

@Service
public class AnoService {
	
	@Autowired
	private AnoRepository anoRepository;
	
	private static final String RECURSO_ANO = "Ano";
	
	
	@Transactional
	public Ano salvar(Ano ano) {			
		return anoRepository.save(ano);
	}
	
	@Transactional
	public Ano alterar(Long id, Ano anoAtualizado) {	
		
		Ano anoAtual = buscarOuFalhar(id);
		
		anoAtual.setTitulo(anoAtualizado.getTitulo());
		
		return anoRepository.save(anoAtual);
	}
	
	@Transactional
	public void excluir(Long id) {
		
		try {
			anoRepository.deleteById(id);
			anoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException(RECURSO_ANO, id);
		
		} catch (DataIntegrityViolationException e) {
			throw new RecursoEmUsoException(RECURSO_ANO);
		}
	}
	
	public List<Ano> listarTodos() { 
		return anoRepository.findAll();
	}
	
	public Ano buscarOuFalhar(Long id) {
		return anoRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_ANO, id));
	}

}
