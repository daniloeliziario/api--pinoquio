package com.eeifpinoquio.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Materia materia;
	
	@ManyToOne
	private Bimestre primeiroBimestre;
	
	@ManyToOne
	private Bimestre segundoBimestre;
	
	@ManyToOne
	private Bimestre terceiroBimestre;
	
	@ManyToOne
	private Bimestre quartoBimestre;
	
	
	public Disciplina(Materia materia) {
		this.materia = materia;
	}

}
