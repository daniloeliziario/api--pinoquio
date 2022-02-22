package com.eeifpinoquio.api.controller.converter.aluno;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;

import com.eeifpinoquio.api.controller.converter.serie.SerieRequest;

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
	private OffsetDateTime dataNascimento;
	
	private SerieRequest serie;

}