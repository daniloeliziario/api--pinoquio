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
public class Aluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String nomePai;
	
	private String nomeMae;
	
	private Serie serie;
	
	private OffsetDateTime dataNascimento;
	
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;
	
	private Boolean ativo = Boolean.TRUE;	
	
	
	public void ativar() {
		setAtivo(true);
	}
	
	public void inativar() {
		setAtivo(false);
	}

}
