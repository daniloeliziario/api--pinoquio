package com.eeifpinoquio.api.controller.converter.bimestre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BimestreRequest {
	
	private Double primeiraNota;
	
	private Double segundaNota;
	
	private Double terceiraNota;
	
	private Double recuperacao;
		
	private Integer falta;
}
