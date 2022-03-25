package com.eeifpinoquio.api.controller.converter.aluno;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnoAlunoResponse {
	
	private String titulo;
	
	private List<MateriaAlunoResponse> materias;

}
