package com.eeifpinoquio.api.controller.converter.aluno;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;

import com.eeifpinoquio.domain.model.Serie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoRequest {
		
	@NotBlank
	private String nome;
	
	@NotBlank
	private OffsetDateTime dataNascimento;
	
	@NotBlank
	private Serie serie;

}
