package com.eeifpinoquio.api.controller.converter.bimestre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BimestreResponse {
	
	private Long id;
	
	private Double primeiraNota;
	
	private Double segundaNota;
	
	private Double terceiraNota;
	
	private Double recuperacao;
	
	private Double media;
	
	private Integer falta;

}
