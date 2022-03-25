package com.eeifpinoquio.api.controller.converter.aluno;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoResponse {
	
	private Long id;
	
	private String nome;
	
	private String nomePai;
	
	private String nomeMae;
	
	private LocalDate dataNascimento;
	
	private AnoAlunoResponse ano;	
	
	private OffsetDateTime dataCadastro;
	
	private OffsetDateTime dataAtualizacao;		
	
}
