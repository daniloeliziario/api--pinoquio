package com.eeifpinoquio.domain.model;

import java.util.List;

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
	
	private String nome;
	
	private List<Bimestre> primeiroBimestre;
	
	private List<Bimestre> segundoBimestre;
	
	private List<Bimestre> terceiroBimestre;
	
	private List<Bimestre> quartoBimestre;

}
