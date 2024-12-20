package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.model.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UsuarioMapper {
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(target = "id", ignore = true)

    Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

}
