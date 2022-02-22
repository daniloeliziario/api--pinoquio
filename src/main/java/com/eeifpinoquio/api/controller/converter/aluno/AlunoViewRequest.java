package com.eeifpinoquio.api.controller.converter.aluno;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoViewRequest {
		
	@NotBlank
	private String nome;
	
	@NotBlank
	private OffsetDateTime dataNascimento;
	
	@NotBlank
	private AlunoViewRequest boletim;

}
