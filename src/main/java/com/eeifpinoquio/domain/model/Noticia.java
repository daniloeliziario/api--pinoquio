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
public class Noticia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String conteudo;
	
	private String autor;
	
	private String imagem;
	
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;

}
