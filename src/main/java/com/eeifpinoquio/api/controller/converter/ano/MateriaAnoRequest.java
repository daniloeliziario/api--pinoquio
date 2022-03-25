package com.eeifpinoquio.api.controller.converter.ano;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaAnoRequest {
	
	@NotBlank
	private String titulo;

}
