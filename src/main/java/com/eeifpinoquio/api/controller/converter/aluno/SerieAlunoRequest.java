package com.eeifpinoquio.api.controller.converter.aluno;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SerieAlunoRequest {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private List<MateriaAlunoRequest> materias;

}
