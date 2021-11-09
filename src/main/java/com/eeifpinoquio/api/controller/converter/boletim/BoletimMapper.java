package com.eeifpinoquio.api.controller.converter.boletim;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.eeifpinoquio.domain.model.Boletim;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoletimMapper {
		
	BoletimResponse convert(Boletim aluno);

}
