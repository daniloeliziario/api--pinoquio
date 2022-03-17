package com.eeifpinoquio.api.controller.converter.aluno;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoRequest {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String nomePai;
	
	@NotBlank
	private String nomeMae;
	
	@NotBlank
	private SerieAlunoRequest serie;
	
	@NotBlank
	private OffsetDateTime dataNascimento;
	
	

}
