package com.eeifpinoquio.api.controller.converter.materia;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.eeifpinoquio.domain.model.Materia;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MateriaMapper {
	
	Materia convert(MateriaRequest materia); 
	
	MateriaResponse convert(Materia materia);
    
    List<MateriaResponse> convert(List<Materia> materias);

}
