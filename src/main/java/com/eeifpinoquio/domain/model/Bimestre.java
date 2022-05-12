package com.eeifpinoquio.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
public class Bimestre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double primeiraNota;
	
	private Double segundaNota;
	
	private Double terceiraNota;
	
	private Double recuperacao;
	
	private Double media;
	
	private Integer falta;
	
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;
	
	
	public Bimestre() {
		
	}
	
	public Bimestre(Long id, Double primeiraNota, Double segundaNota, Double terceiraNota, Double recuperacao, Integer falta) {
		this.id = id;
		this.primeiraNota = primeiraNota;
		this.segundaNota = segundaNota;
		this.terceiraNota = terceiraNota;
		this.recuperacao = recuperacao;
		this.falta = falta;
		
	}

}
