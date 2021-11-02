package com.eeifpinoquio.api.controller.converter.usuario;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.eeifpinoquio.domain.model.Usuario;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {
	 
    Usuario convert(UsuarioRequest usuario); 
    
    UsuarioResponse convert(Usuario usuario);
    
    List<UsuarioResponse> convert(List<Usuario> usuarios);
    
}
