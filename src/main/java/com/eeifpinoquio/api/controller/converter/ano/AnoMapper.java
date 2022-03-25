package com.eeifpinoquio.api.controller.converter.ano;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.eeifpinoquio.domain.model.Ano;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnoMapper {
	
	Ano convert(AnoRequest ano); 
	
	AnoResponse convert(Ano ano);
    
    List<AnoResponse> convert(List<Ano> anos);

}
