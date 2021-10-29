package com.eeifpinoquio.api.controller.converter.noticia;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticiaResponse {
	
	private Long id;
	
	private String titulo;
	
	private String conteudo;
	
	private String autor;
	
	private String imagem;
	
	private OffsetDateTime dataCadastro;
	
	private OffsetDateTime dataAtualizacao;

}
