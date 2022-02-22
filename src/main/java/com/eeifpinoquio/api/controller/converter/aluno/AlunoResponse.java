package com.eeifpinoquio.api.controller.converter.aluno;

import java.time.OffsetDateTime;

import com.eeifpinoquio.api.controller.converter.serie.SerieResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoResponse {
	
	private Long id;
	
	private String nome;
	
	private String nomePai;
	
	private String nomeMae;
	
	private OffsetDateTime dataNascimento;
	
	private OffsetDateTime dataCadastro;
	
	private OffsetDateTime dataAtualizacao;	
	
	private SerieResponse serie;

}
