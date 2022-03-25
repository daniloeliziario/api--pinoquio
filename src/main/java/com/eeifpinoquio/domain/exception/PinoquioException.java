package com.eeifpinoquio.domain.exception;

public class PinoquioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
		
	public PinoquioException(String mensagem) {
		super(mensagem);
	}
	
	public PinoquioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
