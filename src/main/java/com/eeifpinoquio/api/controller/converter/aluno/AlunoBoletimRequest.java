package com.eeifpinoquio.api.controller.converter.aluno;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoBoletimRequest {

	private List<AlunoSerieRequest> series;
}
