package com.eeifpinoquio.api.controller.converter.boletim;

import java.util.List;

import javax.validation.constraints.NotBlank;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SerieBoletimRequest {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private List<MateriaBoletimRequest> materias;
}
