package com.eeifpinoquio.api.controller.converter.boletim;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BimestreBoletimResponse {
	
	private Long id;
	
	private Double primeiraNota;
	
	private Double segundaNota;
	
	private Double terceiraNota;
	
	private Double recuperacao;
	
	private Double media;
	
	private Integer falta;

}
