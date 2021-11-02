package com.eeifpinoquio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eeifpinoquio.api.controller.converter.usuario.UsuarioMapper;
import com.eeifpinoquio.api.controller.converter.usuario.UsuarioRequest;
import com.eeifpinoquio.api.controller.converter.usuario.UsuarioResponse;
import com.eeifpinoquio.api.controller.util.ResponseHeaderUtil;
import com.eeifpinoquio.domain.model.Usuario;
import com.eeifpinoquio.domain.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioResponse adicionar(@RequestBody @Valid UsuarioRequest usuarioRequest) {

		Usuario usuario = mapper.convert(usuarioRequest);

		UsuarioResponse usuarioResponse = mapper.convert(usuarioService.salvar(usuario));

		ResponseHeaderUtil.adicionarLocation(usuarioResponse.getId());

		return usuarioResponse;
	}

	@PutMapping("/{id}")
	public UsuarioResponse atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioRequest usuarioRequest) {

		Usuario usuarioAtualizado = mapper.convert(usuarioRequest);

		return mapper.convert(usuarioService.alterar(id, usuarioAtualizado));
	}

	@GetMapping
	public List<UsuarioResponse> listar() {
		return mapper.convert(usuarioService.listarTodos());
	}

	@GetMapping("/{id}")
	public UsuarioResponse buscar(@PathVariable Long id) {
		return mapper.convert(usuarioService.buscarOuFalhar(id));
	}

}
