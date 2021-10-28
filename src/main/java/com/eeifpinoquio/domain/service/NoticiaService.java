package com.eeifpinoquio.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Noticia;
import com.eeifpinoquio.domain.repository.NoticiaRepository;

@Service
public class NoticiaService {
	
	@Autowired
	private NoticiaRepository noticiaRepository;
	
	private static final String RECURSO_NOTICIA = "Notícia";
	
	
	public List<Noticia> listarTodos() { 
		return noticiaRepository.findAll();
	}
	
	public Noticia buscarOuFalhar(Long id) {
		return noticiaRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_NOTICIA, id));
	}
	
}
