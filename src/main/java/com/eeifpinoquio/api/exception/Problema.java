package com.eeifpinoquio.api.exception;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
class Problema {
	
	private Integer status;
	
	private String type;
	
	private String title;
	
	private String detail;
	
	private OffsetDateTime timestamp;
	
}
