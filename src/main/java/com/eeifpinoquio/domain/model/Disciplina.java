package com.eeifpinoquio.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Materia materia;
	
	private Bimestre primeiroBimestre;
	
	private Bimestre segundoBimestre;
	
	private Bimestre terceiroBimestre;
	
	private Bimestre quartoBimestre;

}
