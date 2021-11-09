package com.eeifpinoquio.api.controller.converter.boletim;

import java.time.OffsetDateTime;

import com.eeifpinoquio.domain.model.Boletim;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletimResponse {
	
	private Long id;
	
	private String nome;
	
	private OffsetDateTime dataNascimento;
	
	private OffsetDateTime dataCadastro;
	
	private OffsetDateTime dataAtualizacao;
	
	private Boletim boletim;

}
