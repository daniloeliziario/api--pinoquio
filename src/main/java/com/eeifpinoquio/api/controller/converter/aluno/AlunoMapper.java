package com.eeifpinoquio.api.controller.converter.aluno;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.eeifpinoquio.domain.model.Aluno;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlunoMapper {
	
	Aluno convert(AlunoRequest aluno); 
	
	AlunoResponse convert(Aluno aluno);
    
    List<AlunoResponse> convert(List<Aluno> alunos);

}
