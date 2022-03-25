package com.eeifpinoquio.api.controller.converter.ano;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnoRequest {
		
	@NotBlank
	private String titulo;
	
	@NotBlank
	private List<MateriaAnoRequest> materias;

}
