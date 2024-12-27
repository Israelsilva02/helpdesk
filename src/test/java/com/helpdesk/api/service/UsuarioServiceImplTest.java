package com.helpdesk.api.service;

import com.helpdesk.api.exception.UsuarioException;
import com.helpdesk.api.mapper.UsuarioMapper;
import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.model.dto.UsuarioDTO;
import com.helpdesk.api.repository.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuarioDTO = new UsuarioDTO();
    }

    @Test
    void testCreateUsuario() {
        when(usuarioMapper.toEntity(usuarioDTO)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        UsuarioDTO createdUsuario = usuarioServiceImpl.createUsuario(usuarioDTO);

        verify(usuarioRepository).save(usuario);
        assertThat(createdUsuario).isEqualTo(usuarioDTO);
    }

    @Test
    void testGetAllUsuarios() {
        List<Usuario> usuarioList = List.of(usuario);
        when(usuarioRepository.findAll()).thenReturn(usuarioList);
        List<UsuarioDTO> usuarioDTOList = List.of(usuarioDTO);
        when(usuarioMapper.usuariosToUsuarioDTO(usuarioList)).thenReturn(usuarioDTOList);

        List<UsuarioDTO> result = usuarioServiceImpl.getAllUsuarios();

        assertThat(result).isEqualTo(usuarioDTOList);
    }

    @Test
    void testGetUsuarioById() {
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        UsuarioDTO result = usuarioServiceImpl.getUsuarioById(id);

        assertThat(result).isEqualTo(usuarioDTO);
    }

    @Test
    void testGetUsuarioById_NotFound() {
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(UsuarioException.class, () -> usuarioServiceImpl.getUsuarioById(id));
    }

    @Test
    void testUpdateUsuario() {
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        doNothing().when(usuarioMapper).toUpdate(usuario, usuarioDTO);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        UsuarioDTO updatedUsuario = usuarioServiceImpl.updateUsuario(id, usuarioDTO);

        verify(usuarioRepository).save(usuario);
        assertThat(updatedUsuario).isEqualTo(usuarioDTO);
    }

    @Test
    void testDeleteUsuario() {
        Long id = 1L;
        when(usuarioRepository.existsById(id)).thenReturn(true);

        usuarioServiceImpl.deleteUsuario(id);

        verify(usuarioRepository).deleteById(id);
    }

    @Test
    void testDeleteUsuario_NotFound() {
        Long id = 1L;
        when(usuarioRepository.existsById(id)).thenReturn(false);

        assertThrows(UsuarioException.class, () -> usuarioServiceImpl.deleteUsuario(id));
    }
}
