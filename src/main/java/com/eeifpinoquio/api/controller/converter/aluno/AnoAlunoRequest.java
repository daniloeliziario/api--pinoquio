package com.eeifpinoquio.api.controller.converter.aluno;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnoAlunoRequest {
	
	@NotBlank
	private String titulo;
	
	@NotNull
	private List<MateriaAlunoRequest> materias;

}
