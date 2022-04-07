package com.eeifpinoquio.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeifpinoquio.domain.exception.PinoquioException;
import com.eeifpinoquio.domain.exception.RecursoEmUsoException;
import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Aluno;
import com.eeifpinoquio.domain.model.Ano;
import com.eeifpinoquio.domain.model.Boletim;
import com.eeifpinoquio.domain.model.Disciplina;
import com.eeifpinoquio.domain.repository.BoletimRepository;

@Service
public class BoletimService {
	
	@Autowired
	private BoletimRepository boletimRepository;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AnoService anoService;
	
	private static final String RECURSO_BOLETIM = "Boletim";	
	
	@Transactional
	public Boletim salvar(Boletim boletim) {	
		
		Long alunoMatricula = boletim.getAluno();
		Aluno alunoAtual = alunoService.buscarOuFalhar(alunoMatricula);
		
		if(alunoAtual.getAtivo().equals(false)) {
			throw new PinoquioException(RECURSO_BOLETIM);
		}
		
		String anoTitulo = boletim.getAno();
		Ano anoAtual = anoService.buscarOuFalhar(anoTitulo);
		
		List<Disciplina> disciplinas = anoAtual.getMaterias()
				.stream()
				.map(materia -> new Disciplina(materia.getTitulo()))
				.collect(Collectors.toList());
		
		boletim.setDisciplinas(disciplinas);
		boletim.setAno(anoAtual.getTitulo());
		
		return boletimRepository.save(boletim);
	}
	
	@Transactional
	public Boletim alterar(Long id, Boletim boletimAtualizado) {	
		
		Boletim boletimAtual = buscarOuFalhar(id);
		
		return boletimRepository.save(boletimAtual);
	}
	
	@Transactional
	public void excluir(Long id) {
		
		try {
			boletimRepository.deleteById(id);
			boletimRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException(RECURSO_BOLETIM, id);
		
		} catch (DataIntegrityViolationException e) {
			throw new RecursoEmUsoException(RECURSO_BOLETIM);
		}
	}
	
	public List<Boletim> listarTodos() { 
		return boletimRepository.findAll();
	}
	
	public Boletim buscarOuFalhar(Long id) {
		return boletimRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_BOLETIM, id));
	}

}
