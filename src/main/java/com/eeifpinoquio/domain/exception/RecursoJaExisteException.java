package com.eeifpinoquio.domain.exception;

public class RecursoJaExisteException extends PinoquioException {

	private static final long serialVersionUID = 1L;

	public RecursoJaExisteException(String recurso) {
		super(recurso);
	}

}
