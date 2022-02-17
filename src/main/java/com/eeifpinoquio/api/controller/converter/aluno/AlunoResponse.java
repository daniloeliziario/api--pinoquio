package com.eeifpinoquio.api.controller.converter.aluno;

import java.time.OffsetDateTime;

import com.eeifpinoquio.domain.model.Disciplina;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoResponse {
	
	private Long id;
	
	private String nome;
	
	private OffsetDateTime dataNascimento;
	
	private Disciplina boletim;
	
	private OffsetDateTime dataCadastro;
	
	private OffsetDateTime dataAtualizacao;	

}
