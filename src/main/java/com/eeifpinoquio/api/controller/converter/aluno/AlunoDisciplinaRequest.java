package com.eeifpinoquio.api.controller.converter.aluno;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoDisciplinaRequest {
		
	@NotBlank
	private String nome;
	
	private List<AlunoBimestreRequest> primeiroBimestre;
	
	private List<AlunoBimestreRequest> segundoBimestre;
	
	private List<AlunoBimestreRequest> terceiroBimestre;
	
	private List<AlunoBimestreRequest> quartoBimestre;

}
