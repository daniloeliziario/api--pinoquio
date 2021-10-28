package com.eeifpinoquio.domain.exception;

public class PinoquioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
		
	public PinoquioException(String recurso) {
		super(recurso);
	}
	
	public PinoquioException(String recurso, Throwable causa) {
		super(recurso, causa);
	}

}
