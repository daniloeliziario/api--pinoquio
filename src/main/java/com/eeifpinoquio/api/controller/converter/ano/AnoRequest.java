package com.eeifpinoquio.api.controller.converter.ano;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnoRequest {
		
	@NotBlank
	private String titulo;
	
	@Valid
	@NotNull
	private List<MateriaAnoRequest> materias;

}
