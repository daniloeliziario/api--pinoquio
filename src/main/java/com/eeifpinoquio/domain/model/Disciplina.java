package com.eeifpinoquio.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	private Materia materia;
	
	@OneToOne
	private Bimestre primeiroBimestre;
	
	@OneToOne
	private Bimestre segundoBimestre;
	
	@OneToOne
	private Bimestre terceiroBimestre;
	
	@OneToOne
	private Bimestre quartoBimestre;
	
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;
	
	
	public Disciplina(Materia materia) {
		this.materia = materia;
	}

}
