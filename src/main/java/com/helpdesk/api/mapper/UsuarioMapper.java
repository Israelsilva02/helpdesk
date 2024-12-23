package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.model.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(uses = {ChamadoMapper.class})
public interface UsuarioMapper {

  UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

  UsuarioDTO toDTO(Usuario usuario);
  List<UsuarioDTO> usuariosToUsuarioDTO(List<Usuario> usuarios);

  Usuario toEntity(UsuarioDTO usuarioDTO);
  List<Usuario> usuarioDTOsToUsuario(List<UsuarioDTO> usuarioDTOs);
}
