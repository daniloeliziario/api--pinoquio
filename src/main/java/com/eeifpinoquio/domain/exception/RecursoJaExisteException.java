package com.eeifpinoquio.domain.exception;

import lombok.Getter;

@Getter
public class RecursoJaExisteException extends PinoquioException {

	private static final long serialVersionUID = 1L;
	
	private final String campo;

	public RecursoJaExisteException(String recurso, String campo) {
		super(recurso);
		this.campo = campo;
	}

}
