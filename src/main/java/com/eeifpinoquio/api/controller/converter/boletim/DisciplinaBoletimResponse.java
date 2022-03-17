package com.eeifpinoquio.api.controller.converter.boletim;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisciplinaBoletimResponse {
	
	private MateriaBoletimResponse materia;
	
	private BimestreBoletimResponse primeiroBimestre;
	
	private BimestreBoletimResponse segundoBimestre;
	
	private BimestreBoletimResponse terceiroBimestre;
	
	private BimestreBoletimResponse quartoBimestre;

}
