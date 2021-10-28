package com.eeifpinoquio.api.exception;

import java.time.OffsetDateTime;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;

@ControllerAdvice
class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		TipoProblema tipoProblema = TipoProblema.MIDIA_NAO_SUPORTADA;

		String detalhe = String.format("A mídia %s/%s não é suportada", ex.getContentType().getType(),
				ex.getContentType().getSubtype());

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		TipoProblema tipoProblema = TipoProblema.METODO_NAO_SUPORTADO;

		String detalhe = String.format("O método recebido na requisição é conhecido pelo servidor de origem, "
				+ "mas não é suportado no caminho do recurso definido. Os métodos suportados nesse caminho são: "
				+ "'%s'", ex.getSupportedHttpMethods());

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		TipoProblema tipoProblema = TipoProblema.RECURSO_NAO_ENCONTRADO;

		String detalhe = String.format("O recurso '%s' não existe", ex.getRequestURL());

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		TipoProblema tipoProblema = TipoProblema.ERRO_NA_REQUISICAO;

		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request,
					tipoProblema);
		}

		String detalhe = String.format("O parâmetro da requisição está inválido. Verifique o parâmetro");

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request, TipoProblema tipoProblema) {

		String detalhe = String.format(
				"O parâmetro '%s' recebeu o valor '%s', que é de um tipo inválido. "
						+ "Corrija e informe um valor compatível com o tipo %s",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	private ResponseEntity<Object> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;

		TipoProblema tipoProblema = TipoProblema.RECURSO_NAO_ENCONTRADO;

		String detalhe = String.format("Não existe %s com id: %s", ex.getMessage(), ex.getId());

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(Exception.class)
	private ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		TipoProblema tipoProblema = TipoProblema.ERRO_NO_SISTEMA;

		String detalhe = "Ocorreu um erro interno inesperado no sistema. "
				+ "Tente novamente e se o problema persistir, entre em contato com o administrador do sistema";

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	private Problema criarProblemaBuilder(HttpStatus status, TipoProblema tipoProblema, String detalhe) {
		return Problema.builder()
				.status(status.value())
				.type(tipoProblema.getUri())
				.title(tipoProblema.getTitulo())
				.detail(detalhe)
				.timestamp(OffsetDateTime.now())
				.build();
	}

}
