package com.eeifpinoquio.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerTest {
	
	@LocalServerPort
	private int port;
		
	private static final String BODY_JSON_CORRETO = "{ \"nome\": \"Danilo\","
			+ "\"email\": \"danilo@escolapinoquio.com.br\", "
			+ "\"senha\": \"123\"}";
	
	private static final String BODY_JSON_INCORRETO = "{ \"nome\": \"Daniel\","
			+ "\"email\": \"daniel@escolapinoquio.com.br\"}";
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
	}
	
	@Test
	void deveRetornarStatus200QuandoConsultarUsuarioComSucesso() {
		RestAssured.given()
			.basePath("/usuarios")
				.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveRetornarStatus200QuandoConsultarUsuarioParametizadoComSucesso() {
		RestAssured.given()
			.basePath("/usuarios/{id}")
				.pathParam("id", 1)
				.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveRetornarStatus201QuandoAdicionarUsuarioComSucesso() {
		RestAssured.given()
			.basePath("/usuarios")
				.body(BODY_JSON_CORRETO)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	void deveRetornarStatus400QuandoAdicionarUsuarioComBodyJsonIncorreto() {
		RestAssured.given()
			.basePath("/usuarios")
			.body(BODY_JSON_INCORRETO)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void deveRetornarStatus404QuandoConsultarUsuarioComRecursoInvalido() {
		RestAssured.given()
			.basePath("/usuarioss")
				.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	void deveRetornarStatus405QuandoConsultarUsuarioComMetodoNaoSuportado() {
		RestAssured.given()
			.basePath("/usuarios")
				.accept(ContentType.JSON)
		.when()
			.put()
		.then()
			.statusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
	}
	
	@Test
	void deveRetornarStatus406QuandoConsultarUsuarioComFormatoNaoAceito() {
		RestAssured.given()
			.basePath("/usuarios")
				.accept(ContentType.XML)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.NOT_ACCEPTABLE.value());
	}
	
	@Test
	void deveRetornarStatus415QuandoAdicionarUsuarioComMediaTypeNaoSuportado() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.given()
			.basePath("/usuarios")
				.body(BODY_JSON_CORRETO)
				.contentType(ContentType.XML)
				.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
	}

}
