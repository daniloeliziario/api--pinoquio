package com.eeifpinoquio.api.controller.converter.bimestre;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BimestreRequest {
	
	@Max(10)
	@Min(0)
	private Double primeiraNota;
	
	@Max(10)
	@Min(0)
	private Double segundaNota;
	
	@Max(10)
	@Min(0)
	private Double terceiraNota;
	
	@Max(10)
	@Min(0)
	private Double recuperacao;
		
	@PositiveOrZero
	private Integer falta;
}
