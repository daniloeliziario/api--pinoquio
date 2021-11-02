package com.eeifpinoquio.api.controller.converter.noticia;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticiaRequest {
		
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String conteudo;
	
	@NotBlank
	private String autor;
	
	@NotBlank
	private String imagem;

}
