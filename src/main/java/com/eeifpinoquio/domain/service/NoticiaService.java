package com.eeifpinoquio.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeifpinoquio.domain.exception.RecursoEmUsoException;
import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Noticia;
import com.eeifpinoquio.domain.repository.NoticiaRepository;

@Service
public class NoticiaService {
	
	@Autowired
	private NoticiaRepository noticiaRepository;
	
	private static final String RECURSO_NOTICIA = "Notícia";
	
	
	@Transactional
	public Noticia salvar(Noticia noticia) {			
		return noticiaRepository.save(noticia);
	}
	
	@Transactional
	public Noticia alterar(Long id, Noticia noticiaAtualizado) {	
		
		Noticia noticiaAtual = buscarOuFalhar(id);
		
		noticiaAtual.setTitulo(noticiaAtualizado.getTitulo());
		noticiaAtual.setConteudo(noticiaAtualizado.getConteudo());
		noticiaAtual.setAutor(noticiaAtualizado.getAutor());
		noticiaAtual.setImagem(noticiaAtualizado.getImagem());
		
		return noticiaRepository.save(noticiaAtual);
	}
	
	@Transactional
	public void excluir(Long id) {
		
		try {
			noticiaRepository.deleteById(id);
			noticiaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException(RECURSO_NOTICIA, id);
		
		} catch (DataIntegrityViolationException e) {
			throw new RecursoEmUsoException(RECURSO_NOTICIA);
		}
	}
	
	public List<Noticia> listarTodos() { 
		return noticiaRepository.findAll();
	}
	
	public Noticia buscarOuFalhar(Long id) {
		return noticiaRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_NOTICIA, id));
	}
	
}
