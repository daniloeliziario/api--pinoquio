package com.eeifpinoquio.api.controller.converter.materia;

import javax.validation.constraints.NotBlank;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaRequest {
		
	@NotBlank
	private String nome;

}