package com.eeifpinoquio.domain.exception;

import lombok.Getter;

@Getter
public class RecursoEmUsoException extends PinoquioException {

	private static final long serialVersionUID = 1L;
	
	private final Long id;

	public RecursoEmUsoException(String mensagem, Long id) {
		super(mensagem);
		this.id = id;
	}

}
