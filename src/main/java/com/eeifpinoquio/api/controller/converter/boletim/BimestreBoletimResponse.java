package com.eeifpinoquio.api.controller.converter.boletim;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BimestreBoletimResponse {
	
	private String primeiraNota;
	
	private String segundaNota;
	
	private String terceiraNota;
	
	private String recuperacao;
	
	private String media;
	
	private Integer falta;

}
