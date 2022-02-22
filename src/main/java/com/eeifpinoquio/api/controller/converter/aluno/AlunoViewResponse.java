package com.eeifpinoquio.api.controller.converter.aluno;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoViewResponse {
	
	private Long id;
	
	private String nome;
	
	private OffsetDateTime dataNascimento;
	
	private BoletimViewResponse boletim;

}
