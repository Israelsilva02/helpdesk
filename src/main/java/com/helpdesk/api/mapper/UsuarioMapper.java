package com.helpdesk.api.mapper;


import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.model.dto.UsuarioDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static UsuarioDTO toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .chamadoIds(usuario.getChamados().stream()
                        .map(Chamado::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Usuario toEntity(UsuarioDTO usuarioDto) {
        if (usuarioDto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setId(usuarioDto.getId());
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());

        return usuario;
    }

    public static List<UsuarioDTO> toDtoList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioMapper::toDto)
                .collect(Collectors.toList());
    }
}
