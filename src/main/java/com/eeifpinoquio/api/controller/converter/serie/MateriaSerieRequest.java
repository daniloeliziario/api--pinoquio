package com.eeifpinoquio.api.controller.converter.serie;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaSerieRequest {
	
	@NotBlank
	private String nome;

}
