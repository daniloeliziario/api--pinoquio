package com.eeifpinoquio.api.exception;

import lombok.Getter;

@Getter
public enum TipoProblema {
	
	RECURSO_JA_EXISTE("/recurso-ja-existe", "Recurso já existe"),
	RECURSO_EM_USO("/recurso-em-uso", "Recurso em uso"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	RECURSO_INATIVO("/recurso-inativo", "Recurso inativo"),
	ERRO_NA_REQUISICAO("/erro-na-requisicao", "Erro na requisição"),
	METODO_NAO_SUPORTADO("/metodo-nao-suportado", "Método não suportado"),
	ERRO_NO_SISTEMA("/erro-no-sistema", "Erro no sistema"),
	MIDIA_NAO_SUPORTADA("/midia-nao-suportada", "Tipo de mídia não suportada"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");
	
	private String titulo;
	
	private String uri;
	
	TipoProblema(String uri, String titulo) {
		this.uri = uri;
		this.titulo = titulo;
	}
	
}
