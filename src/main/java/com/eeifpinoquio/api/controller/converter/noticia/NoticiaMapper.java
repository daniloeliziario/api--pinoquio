package com.eeifpinoquio.api.controller.converter.noticia;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.eeifpinoquio.domain.model.Noticia;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoticiaMapper {
	
	Noticia convert(NoticiaRequest noticia); 
	
	NoticiaResponse convert(Noticia noticia);
    
    List<NoticiaResponse> convert(List<Noticia> noticias);

}
