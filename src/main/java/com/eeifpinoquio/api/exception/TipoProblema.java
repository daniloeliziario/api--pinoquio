package com.eeifpinoquio.api.exception;

import lombok.Getter;

@Getter
public enum TipoProblema {
	
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ERRO_NA_REQUISICAO("/erro-na-requisicao", "Erro na requisição"),
	METODO_NAO_SUPORTADO("/metodo-nao-suportado", "Método não suportado"),
	ERRO_NO_SISTEMA("/erro-no-sistema", "Erro no sistema"),
	MIDIA_NAO_SUPORTADA("/midia-nao-suportada", "Tipo de mídia não suportada");
	
	private String titulo;
	
	private String uri;
	
	TipoProblema(String uri, String titulo) {
		this.uri = "https://escolapinoquio.com" + uri;
		this.titulo = titulo;
	}
	
}
