package com.eeifpinoquio.api.exception;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eeifpinoquio.domain.exception.PinoquioException;
import com.eeifpinoquio.domain.exception.RecursoEmUsoException;
import com.eeifpinoquio.domain.exception.RecursoInativoException;
import com.eeifpinoquio.domain.exception.RecursoJaExisteException;
import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

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
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String paramentroInvalido = paramentrosInvalidos(ex.getFieldErrors());

		TipoProblema tipoProblema = TipoProblema.ERRO_NA_REQUISICAO;

		String detalhe = String.format("O parâmetro %s. Faça o preenchimento correto e tente novamente",
				paramentroInvalido);

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

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Throwable rootCause = ex.getRootCause();

		TipoProblema tipoProblema = TipoProblema.ERRO_NA_REQUISICAO;

		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request, tipoProblema);
		} else if (rootCause instanceof UnrecognizedPropertyException) {
			return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request, tipoProblema);
		}

		String detalhe = "O corpo da requisição está inválido. Verifique erro de sintaxe";

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request, TipoProblema tipoProblema) {

		String caminho = concatenarParametros(ex.getPath());

		String detalhe = String.format(
				"A propriedade '%s' recebeu o valor '%s', que é do tipo inválido. "
						+ "Corrija e informe um valor compatível com o tipo %s",
				caminho, ex.getValue(), ex.getTargetType().getSimpleName());

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request, TipoProblema tipoProblema) {

		String caminho = concatenarParametros(ex.getPath());

		String detalhe = String.format("A propriedade '%s' é inválida. Remova a propriedade e tente novamente",
				caminho);

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(PinoquioException.class)
	private ResponseEntity<Object> handlePinoquioException(PinoquioException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		TipoProblema tipoProblema = TipoProblema.ERRO_NEGOCIO;

		String detalhe = String.format("Já existe um(a) %s cadastrado com esse campo", ex.getMessage());

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(RecursoJaExisteException.class)
	private ResponseEntity<Object> handleRecursoJaExiste(RecursoJaExisteException ex, WebRequest request) {

		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

		TipoProblema tipoProblema = TipoProblema.RECURSO_JA_EXISTE;

		String detalhe = String.format("%1$s já existe. Por favor, insira um(a) %1$s(a) com um %2$s diferente",
				ex.getMessage(), ex.getCampo());

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(RecursoEmUsoException.class)
	private ResponseEntity<Object> handleRecursoEmUso(RecursoEmUsoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.CONFLICT;

		TipoProblema tipoProblema = TipoProblema.RECURSO_EM_USO;

		String detalhe = String.format("O %s com id %s está sendo utilizado por outro(s) recurso(s) e por isso não é possível executar a operação", ex.getMessage(), ex.getId());

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	private ResponseEntity<Object> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;

		TipoProblema tipoProblema = TipoProblema.RECURSO_NAO_ENCONTRADO;

		String detalhe = String.format("Não existe %s com id %s", ex.getMessage(), ex.getId());

		Problema problema = criarProblemaBuilder(status, tipoProblema, detalhe);

		return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(RecursoInativoException.class)
	private ResponseEntity<Object> handleRecursoInativo(RecursoInativoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.FORBIDDEN;

		TipoProblema tipoProblema = TipoProblema.RECURSO_INATIVO;

		String detalhe = String.format("Não é possível realizar a operação porque o recurso %s está inativo", ex.getMessage());

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

	private String concatenarParametros(List<Reference> references) {
		return references.stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
	}

	private String paramentrosInvalidos(List<FieldError> campos) {
		return campos.stream()
				.map(camp -> camp.getField() + " " + camp.getDefaultMessage())
				.limit(1)
				.collect(Collectors.joining());
	}

}
