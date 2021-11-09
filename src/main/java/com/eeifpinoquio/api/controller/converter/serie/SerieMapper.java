package com.eeifpinoquio.api.controller.converter.serie;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.eeifpinoquio.domain.model.Serie;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SerieMapper {
	
	Serie convert(SerieRequest serie); 
	
	SerieResponse convert(Serie serie);
    
    List<SerieResponse> convert(List<Serie> series);

}
