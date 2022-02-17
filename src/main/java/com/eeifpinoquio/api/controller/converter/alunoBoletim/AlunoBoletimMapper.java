package com.eeifpinoquio.api.controller.converter.alunoBoletim;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.eeifpinoquio.domain.model.Boletim;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlunoBoletimMapper {
		
	AlunoBoletimResponse convert(Boletim boletim);

}
