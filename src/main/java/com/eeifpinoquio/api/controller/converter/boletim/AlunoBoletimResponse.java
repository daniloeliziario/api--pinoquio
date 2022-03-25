package com.eeifpinoquio.api.controller.converter.boletim;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoBoletimResponse {

	private String nome;
	
	private AnoBoletimResponse ano;
}
