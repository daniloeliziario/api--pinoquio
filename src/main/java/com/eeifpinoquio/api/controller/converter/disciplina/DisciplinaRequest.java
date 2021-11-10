package com.eeifpinoquio.api.controller.converter.disciplina;

import javax.validation.constraints.NotBlank;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisciplinaRequest {
		
	@NotBlank
	private String nome;

}
