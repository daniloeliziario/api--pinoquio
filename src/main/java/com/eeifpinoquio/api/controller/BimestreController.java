package com.eeifpinoquio.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eeifpinoquio.api.controller.converter.bimestre.BimestreMapper;
import com.eeifpinoquio.api.controller.converter.bimestre.BimestreRequest;
import com.eeifpinoquio.api.controller.converter.bimestre.BimestreResponse;
import com.eeifpinoquio.domain.model.Bimestre;
import com.eeifpinoquio.domain.service.BimestreService;

@RestController
@RequestMapping(value = "/boletins/{idBoletim}/disciplinas/{idDisciplina}/bimestres", produces = MediaType.APPLICATION_JSON_VALUE)
public class BimestreController {
	
	@Autowired
	private BimestreService bimestreService;

	@Autowired
	private BimestreMapper mapper;
	
	@PutMapping("/{id}")
	public BimestreResponse atualizar(@PathVariable Long idBoletim, @PathVariable Long idDisciplina, @PathVariable Long id, @RequestBody @Valid BimestreRequest bimestreRequest) {

		Bimestre bimestreAtualizado = mapper.convert(bimestreRequest);

		return mapper.convert(bimestreService.alterar(idBoletim, idDisciplina, id, bimestreAtualizado));
	}

}
