package com.eeifpinoquio.api.controller.converter.boletim;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoBoletimRequest {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private AnoBoletimRequest ano;

}
