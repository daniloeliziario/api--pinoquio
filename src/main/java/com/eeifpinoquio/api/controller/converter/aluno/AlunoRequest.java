package com.eeifpinoquio.api.controller.converter.aluno;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotNull
	private LocalDate dataNascimento;
	
	@Valid
	@NotNull
	private AnoAlunoRequest ano;	

}
