package com.eeifpinoquio.api.controller.converter.serie;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SerieResponse {
	
	private Long id;
	
	private String nome;
	
	private List<MateriaSerieResponse> materias;

}
