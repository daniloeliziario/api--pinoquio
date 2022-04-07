package com.eeifpinoquio.api.controller.converter.boletim;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletimRequest {
	
	@NotNull
	private Long aluno;
	
	@NotBlank
	private String ano;

}
