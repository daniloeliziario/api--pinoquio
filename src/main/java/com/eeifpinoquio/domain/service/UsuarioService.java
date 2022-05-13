package com.eeifpinoquio.domain.service;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.ObjectUtils.notEqual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeifpinoquio.domain.exception.PinoquioException;
import com.eeifpinoquio.domain.exception.RecursoJaExisteException;
import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Usuario;
import com.eeifpinoquio.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private static final String RECURSO_USUARIO = "Usuário";
	
	@Transactional
	public Usuario salvar(Usuario usuario) {	
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		
		if(usuarioExistente.isPresent()) {
			throw new RecursoJaExisteException(RECURSO_USUARIO);
		} 
		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public Usuario alterar(Long id, Usuario usuarioAtualizado) {	
		
		Usuario usuarioAtual = buscarOuFalhar(id);
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuarioAtualizado.getEmail());
		
		if(usuarioExistente.isPresent() && notEqual(usuarioAtual, usuarioExistente.get())) {
			throw new PinoquioException(RECURSO_USUARIO);
		}
		
		usuarioAtual.setNome(usuarioAtualizado.getNome());
		usuarioAtual.setEmail(usuarioAtualizado.getEmail());
		
		return usuarioRepository.save(usuarioAtual);
	}
	
	@Transactional
	public void ativar(Long id) {
		
		Usuario usuarioAtual = buscarOuFalhar(id);
		
		usuarioAtual.ativar();
	}
	
	@Transactional
	public void inativar(Long id) {
		
		Usuario usuarioAtual = buscarOuFalhar(id);
		
		usuarioAtual.inativar();
	}	
	
	public List<Usuario> listarTodos() { 
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarOuFalhar(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_USUARIO, id));
	}
	
}
