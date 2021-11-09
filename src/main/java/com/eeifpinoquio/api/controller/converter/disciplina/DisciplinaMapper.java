package com.eeifpinoquio.api.controller.converter.disciplina;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.eeifpinoquio.domain.model.Disciplina;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DisciplinaMapper {
	
	Disciplina convert(DisciplinaRequest disciplina); 
	
	DisciplinaResponse convert(Disciplina disciplina);
    
    List<DisciplinaResponse> convert(List<Disciplina> disciplinas);

}
