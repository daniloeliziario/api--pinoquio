package com.eeifpinoquio.api.controller.converter.professor;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.eeifpinoquio.domain.model.Professor;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfessorMapper {
	
	Professor convert(ProfessorRequest professor); 
	
	ProfessorResponse convert(Professor professor);
    
    List<ProfessorResponse> convert(List<Professor> professores);

}
