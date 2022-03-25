package com.eeifpinoquio.api.controller.converter.materia;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaResponse {
	
	private Long id;
	
	private String titulo;
	
	private String descricao;
	
	private ProfessorMateriaResponse professor;
}
