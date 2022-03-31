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
import com.eeifpinoquio.domain.model.Aluno;
import com.eeifpinoquio.domain.model.Ano;
import com.eeifpinoquio.domain.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private AnoService anoService;
	
	private static final String RECURSO_ALUNO = "Aluno";
	
	@Transactional
	public Aluno salvar(Aluno aluno) {		
		
		Optional<Aluno> alunoExistente = alunoRepository.findByNome(aluno.getNome());
		
		if(alunoExistente.isPresent()) {
			throw new RecursoJaExisteException(RECURSO_ALUNO);
		}
		
		Long anoId = aluno.getAno().getId();		
		Ano anoAtual = anoService.buscarOuFalhar(anoId);
		
		aluno.setAno(anoAtual);
		
		return alunoRepository.save(aluno);
	}
	
	@Transactional
	public Aluno alterar(Long id, Aluno alunoAtualizado) {	
		
		Aluno alunoAtual = buscarOuFalhar(id);
		
		Optional<Aluno> alunoExistente = alunoRepository.findByNome(alunoAtualizado.getNome());
		
		if(alunoExistente.isPresent() && notEqual(alunoAtual, alunoExistente.get())) {
			throw new PinoquioException(RECURSO_ALUNO);
		}
		
		Long anoId = alunoAtualizado.getAno().getId();		
		Ano anoAtual = anoService.buscarOuFalhar(anoId);		
		
		alunoAtual.setNome(alunoAtualizado.getNome());
		alunoAtual.setNomePai(alunoAtualizado.getNomePai());
		alunoAtual.setNomeMae(alunoAtualizado.getNomeMae());
		alunoAtual.setDataNascimento(alunoAtualizado.getDataNascimento());
		alunoAtual.setAno(anoAtual);
		
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
	
	@Transactional
	public void ativar(Long alunoId) {
		
		Aluno alunoAtual = buscarOuFalhar(alunoId);
		
		alunoAtual.ativar();
	}
	
	@Transactional
	public void inativar(Long alunoId) {
		
		Aluno alunoAtual = buscarOuFalhar(alunoId);
		
		alunoAtual.inativar();
	}	
	
	public List<Aluno> listarTodos() { 
		return alunoRepository.findAll();
	}
	
	public Aluno buscarOuFalhar(Long id) {
		return alunoRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_ALUNO, id));
	}

}
