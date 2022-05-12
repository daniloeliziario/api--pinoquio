package com.eeifpinoquio.api.controller.converter.bimestre;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.eeifpinoquio.domain.model.Bimestre;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BimestreMapper {
	
	Bimestre convert(BimestreRequest bimestre); 
	
	BimestreResponse convert(Bimestre bimestre);

}
