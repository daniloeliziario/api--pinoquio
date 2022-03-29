package com.eeifpinoquio.api.controller.converter.ano;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaAnoRequest {
	
	@NotNull
	private Long id;

}
