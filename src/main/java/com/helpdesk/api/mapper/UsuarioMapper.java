package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.model.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.List;

@Mapper(componentModel = "spring",uses = {ChamadoMapper.class})
public interface UsuarioMapper {
    @Mapping(target = "chamadoIds", ignore = true)
    UsuarioDTO toDTO(Usuario usuario);

    List<UsuarioDTO> usuariosToUsuarioDTO(List<Usuario> usuarios);

    Usuario toEntity(UsuarioDTO usuarioDTO);

    List<Usuario> usuarioDTOsToUsuario(List<UsuarioDTO> usuarioDTOs);

    void toUpdate(@MappingTarget Usuario usuario, UsuarioDTO usuarioDTO);
}
