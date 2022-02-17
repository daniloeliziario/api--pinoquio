package com.eeifpinoquio.api.controller.converter.alunoBoletim;

import java.time.OffsetDateTime;
import java.util.List;

import com.eeifpinoquio.domain.model.Disciplina;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoBoletimResponse {
	
	private Long id;
	
	private String nome;
	
	private OffsetDateTime dataNascimento;
	
	private OffsetDateTime dataCadastro;
	
	private OffsetDateTime dataAtualizacao;
	
	private List<Disciplina> boletins;

}
