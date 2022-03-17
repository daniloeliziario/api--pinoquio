package com.eeifpinoquio.api.controller.converter.boletim;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.eeifpinoquio.domain.model.Boletim;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoletimMapper {
	
	Boletim convert(BoletimRequest boletim); 
	
	BoletimResponse convert(Boletim boletim);
    
    List<BoletimResponse> convert(List<Boletim> boletims);

}
