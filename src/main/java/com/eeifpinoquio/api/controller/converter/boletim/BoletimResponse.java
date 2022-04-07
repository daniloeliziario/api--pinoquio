package com.eeifpinoquio.api.controller.converter.boletim;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletimResponse {
	
	private Long id;
	
	private Long aluno;
	
	private List<DisciplinaBoletimResponse> disciplinas;

}
