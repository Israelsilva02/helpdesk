package com.helpdesk.api.mapper;


import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.model.dto.UsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

public class UsuarioMapperTest {

    private UsuarioMapper usuarioMapper;

    @BeforeEach
    public void setUp() {
        usuarioMapper = Mappers.getMapper(UsuarioMapper.class);
    }

    @Test
    public void testToDTO() {
        Usuario usuario = new Usuario();
        usuario.setCustomerId(1L);
        usuario.setNome("israel");
        usuario.setEmail("israel@gmail.com");

        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario);

        assertNotNull(usuarioDTO);
        assertEquals(usuario.getCustomerId(), usuarioDTO.getCustomerId());
        assertEquals(usuario.getNome(), usuarioDTO.getNome());
        assertEquals(usuario.getEmail(), usuarioDTO.getEmail());
    }

    @Test
    public void testToEntity() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCustomerId(1L);
        usuarioDTO.setNome("israel");
        usuarioDTO.setEmail("israel@gmail.com");


        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);

        assertNotNull(usuario);
        assertEquals(usuarioDTO.getCustomerId(), usuario.getCustomerId());
        assertEquals(usuarioDTO.getNome(), usuario.getNome());
        assertEquals(usuarioDTO.getEmail(), usuario.getEmail());
    }

    @Test
    public void testUsuariosToUsuarioDTO() {
        Usuario usuario = new Usuario();
        usuario.setCustomerId(1L);
        usuario.setNome("israel");
        usuario.setEmail("israel@gmail.com");

        List<UsuarioDTO> usuarioDTOList = usuarioMapper.usuariosToUsuarioDTO(Collections.singletonList(usuario));

        assertNotNull(usuarioDTOList);
        assertEquals(1, usuarioDTOList.size());
        assertEquals(usuario.getCustomerId(), usuarioDTOList.get(0).getCustomerId());
        assertEquals(usuario.getNome(), usuarioDTOList.get(0).getNome());
    }

    @Test
    public void testUsuarioDTOsToUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCustomerId(1L);
        usuarioDTO.setNome("israel");
        usuarioDTO.setEmail("israell@gmail.com");

        List<Usuario> usuarios = usuarioMapper.usuarioDTOsToUsuario(Collections.singletonList(usuarioDTO));

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals(usuarioDTO.getCustomerId(), usuarios.get(0).getCustomerId());
        assertEquals(usuarioDTO.getNome(), usuarios.get(0).getNome());
    }

    @Test
    public void testToUpdate() {
        Usuario usuario = new Usuario();
        usuario.setCustomerId(1L);
        usuario.setNome("israel");
        usuario.setEmail("israell@gmail.com");

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("israel bernardo");
        usuarioDTO.setEmail("israell@gmail.com");

        usuarioMapper.toUpdate(usuario, usuarioDTO);

        assertEquals("israel bernardo", usuario.getNome());
        assertEquals(usuarioDTO.getCustomerId(), usuario.getCustomerId());
        assertEquals(usuario.getEmail(), usuarioDTO.getEmail());
    }
}
