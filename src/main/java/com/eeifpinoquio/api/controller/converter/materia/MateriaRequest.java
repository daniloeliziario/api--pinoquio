package com.eeifpinoquio.api.controller.converter.materia;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaRequest {
		
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String descricao;
	
	@Valid
	@NotNull
	private ProfessorMateriaRequest professor;

}
