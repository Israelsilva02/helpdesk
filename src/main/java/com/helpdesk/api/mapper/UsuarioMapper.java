package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.model.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

    Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);
}
