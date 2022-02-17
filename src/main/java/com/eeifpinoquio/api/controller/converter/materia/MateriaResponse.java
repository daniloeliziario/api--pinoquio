package com.eeifpinoquio.api.controller.converter.materia;

import java.util.List;

import com.eeifpinoquio.domain.model.Bimestre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaResponse {
	
	private Long id;
	
	private String nome;
	
	private List<Bimestre> primeiroBimestre;
	
	private List<Bimestre> segundoBimestre;
	
	private List<Bimestre> terceiroBimestre;
	
	private List<Bimestre> quartoBimestre;

}
