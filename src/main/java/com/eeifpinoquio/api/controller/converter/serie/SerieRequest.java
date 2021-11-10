package com.eeifpinoquio.api.controller.converter.serie;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.eeifpinoquio.api.controller.converter.disciplina.DisciplinaRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SerieRequest {
		
	@NotBlank
	private String nome;
		
	@NotBlank
	private List<DisciplinaRequest> disciplinas;

}
