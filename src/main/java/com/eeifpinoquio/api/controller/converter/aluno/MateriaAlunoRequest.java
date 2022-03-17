package com.eeifpinoquio.api.controller.converter.aluno;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaAlunoRequest {
	
	@NotBlank
	private String nome;

}
