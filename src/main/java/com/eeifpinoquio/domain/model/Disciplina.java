package com.eeifpinoquio.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String materia;
	
	@OneToOne(cascade={CascadeType.ALL})
	private Bimestre primeiroBimestre;
	
	@OneToOne(cascade={CascadeType.ALL})
	private Bimestre segundoBimestre;
	
	@OneToOne(cascade={CascadeType.ALL})
	private Bimestre terceiroBimestre;
	
	@OneToOne(cascade={CascadeType.ALL})
	private Bimestre quartoBimestre;
	
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;
	
	
	public Disciplina() {
		
	}
	
	public Disciplina(String materia, Bimestre primeiroBimestre, Bimestre segundoBimestre, Bimestre terceiroBimestre, Bimestre quartoBimestre) {
		this.materia = materia;
		this.primeiroBimestre = primeiroBimestre;
		this.segundoBimestre = segundoBimestre;
		this.terceiroBimestre = terceiroBimestre;
		this.quartoBimestre = quartoBimestre;
	}

}
