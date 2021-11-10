package com.eeifpinoquio.api.controller.converter.aluno;

import java.time.OffsetDateTime;

import com.eeifpinoquio.domain.model.Boletim;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoBoletimResponse {
	
	private Long id;
	
	private String nome;
	
	private OffsetDateTime dataNascimento;
	
	private Boletim boletim;

}
