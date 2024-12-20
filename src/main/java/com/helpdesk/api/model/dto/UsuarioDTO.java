package com.helpdesk.api.model.dto;


import java.util.List;


public record UsuarioDTO(
        String nome,
        String email,
        List<Long> chamadoIds) {

}
