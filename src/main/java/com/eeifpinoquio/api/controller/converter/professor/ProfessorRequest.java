package com.eeifpinoquio.api.controller.converter.professor;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorRequest {
	
	@NotBlank
	private String nome;
	
}
