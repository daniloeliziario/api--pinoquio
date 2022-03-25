package com.eeifpinoquio.api.controller.converter.ano;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnoResponse {
	
	private Long id;
	
	private String titulo;
	
	private List<MateriaAnoResponse> materias;

}
