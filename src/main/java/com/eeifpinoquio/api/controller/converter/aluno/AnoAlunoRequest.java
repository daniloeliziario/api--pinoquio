package com.eeifpinoquio.api.controller.converter.aluno;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnoAlunoRequest {
	
	@NotNull
	private Long id;

}
