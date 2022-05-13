package com.eeifpinoquio.api.controller.converter.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {
	
	private Long id;
	
	private String nome;
	
	private String email;
	
	private Boolean ativo;

}
