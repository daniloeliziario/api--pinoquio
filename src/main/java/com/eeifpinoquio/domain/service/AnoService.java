package com.eeifpinoquio.domain.service;

import static org.apache.commons.lang3.ObjectUtils.notEqual;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeifpinoquio.domain.exception.PinoquioException;
import com.eeifpinoquio.domain.exception.RecursoEmUsoException;
import com.eeifpinoquio.domain.exception.RecursoJaExisteException;
import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Ano;
import com.eeifpinoquio.domain.model.Materia;
import com.eeifpinoquio.domain.repository.AnoRepository;

@Service
public class AnoService {
	
	@Autowired
	private AnoRepository anoRepository;
	
	@Autowired
	private MateriaService materiaService;
	
	private static final String RECURSO_ANO = "Ano";
	
	
	@Transactional
	public Ano salvar(Ano ano) {			
		
		Optional<Ano> anoExistente = anoRepository.findByTitulo(ano.getTitulo());
		
		if(anoExistente.isPresent()) {
			throw new RecursoJaExisteException(RECURSO_ANO);
		}
		
		List<Materia> materias = ano.getMaterias().stream()
				.map(materiaAtual -> materiaService.buscarOuFalhar(materiaAtual.getId()))
				.collect(Collectors.toList());
		
		ano.setMaterias(materias);
		
		return anoRepository.save(ano);
	}
	
	@Transactional
	public Ano alterar(Long id, Ano anoAtualizado) {
		
		Ano anoAtual = buscarOuFalhar(id);
		
		Optional<Ano> anoExistente = anoRepository.findByTitulo(anoAtualizado.getTitulo());
		
		if(anoExistente.isPresent() && notEqual(anoAtual, anoExistente.get())) {
			throw new PinoquioException(RECURSO_ANO);
		}
		
		List<Materia> materias = anoAtualizado.getMaterias().stream()
				.map(materiaAtual -> materiaService.buscarOuFalhar(materiaAtual.getId()))
				.collect(Collectors.toList());
		
		anoAtual.setTitulo(anoAtualizado.getTitulo());
		anoAtual.setMaterias(materias);
		
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
	
	public Ano buscarOuFalhar(String titulo) {
		return anoRepository.findByTitulo(titulo)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_ANO, 1l));
	}

}
