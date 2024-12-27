package com.helpdesk.api.controller.impl;

import com.helpdesk.api.model.dto.UsuarioDTO;
import com.helpdesk.api.service.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UsuarioControllerImplTest {

    private UsuarioControllerImpl usuarioController;
    private UsuarioServiceImpl usuarioServiceImpl;

    @BeforeEach
    void setUp() {
        usuarioServiceImpl = mock(UsuarioServiceImpl.class);
        usuarioController = new UsuarioControllerImpl(usuarioServiceImpl);
    }

    @Test
    void deveCriarUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("israel");

        when(usuarioServiceImpl.createUsuario(ArgumentMatchers.any(UsuarioDTO.class)))
                .thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = usuarioController.createUsuario(usuarioDTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody().getNome()).isEqualTo("israel");

        verify(usuarioServiceImpl).createUsuario(any(UsuarioDTO.class));
    }

    @Test
    void deveListarTodosUsuarios() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNome("israel");

        List<UsuarioDTO> usuarios = Arrays.asList(usuario);

        when(usuarioServiceImpl.getAllUsuarios()).thenReturn(usuarios);

        ResponseEntity<List<UsuarioDTO>> response = usuarioController.getAllUsuarios();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().size()).isEqualTo(1);
        assertThat(response.getBody().get(0).getNome()).isEqualTo("israel");

        verify(usuarioServiceImpl).getAllUsuarios();
    }

    @Test
    void deveBuscarUsuarioPorId() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("israel");

        when(usuarioServiceImpl.getUsuarioById(1L)).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = usuarioController.getUsuarioById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getNome()).isEqualTo("israel");

        verify(usuarioServiceImpl).getUsuarioById(1L);
    }

    @Test
    void deveAtualizarUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("israel bernardo");

        when(usuarioServiceImpl.updateUsuario(1L, usuarioDTO)).thenReturn(usuarioDTO);

        ResponseEntity<UsuarioDTO> response = usuarioController.updateUsuario(1L, usuarioDTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getNome()).isEqualTo("israel bernardo");

        verify(usuarioServiceImpl).updateUsuario(1L, usuarioDTO);
    }

    @Test
    void deveDeletarUsuario() {
        doNothing().when(usuarioServiceImpl).deleteUsuario(1L);

        ResponseEntity<Void> response = usuarioController.deleteUsuario(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(usuarioServiceImpl).deleteUsuario(1L);
    }
}
