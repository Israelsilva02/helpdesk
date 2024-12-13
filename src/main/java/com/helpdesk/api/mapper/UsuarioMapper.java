package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.model.dto.UsuarioDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static List<UsuarioDTO> toDtoUsuario(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(entity -> toDtoUsuarioDto(entity))
                .collect(Collectors.toList());
    }

    public static UsuarioDTO toDtoUsuarioDto(Usuario usuario) {
        if (Objects.nonNull(usuario)) {
            return UsuarioDTO.builder()
                    .nome(usuario.getNome())
                    .email(usuario.getEmail())
                    .build();
        } else {
            return UsuarioDTO.builder().build();
        }
    }

    public static Usuario toEntityUsuario(UsuarioDTO usuarioDTO) {

        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .build();
    }
}
