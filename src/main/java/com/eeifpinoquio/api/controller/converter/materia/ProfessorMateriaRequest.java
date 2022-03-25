package com.eeifpinoquio.api.controller.converter.materia;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorMateriaRequest {
	
	@NotNull
	private Long id;
	
}
