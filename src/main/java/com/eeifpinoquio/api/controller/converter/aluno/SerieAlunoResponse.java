package com.eeifpinoquio.api.controller.converter.aluno;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SerieAlunoResponse {
	
	private String nome;
	
	private List<MateriaAlunoResponse> materias;

}
