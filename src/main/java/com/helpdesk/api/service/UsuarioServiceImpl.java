package com.helpdesk.api.service;

import com.helpdesk.api.exception.UsuarioException;
import com.helpdesk.api.mapper.UsuarioMapper;
import com.helpdesk.api.model.Usuario;

import com.helpdesk.api.model.dto.UsuarioDTO;
import com.helpdesk.api.repository.UsuarioRepository;
import com.helpdesk.api.util.MessageConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(savedUsuario);
    }

    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.usuariosToUsuarioDTO(usuarios);
    }

    public UsuarioDTO getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioException(MessageConstants.USUARIO_NAO_ENCONTRADO_COM_ID + id));
        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO updateUsuario(Long id, UsuarioDTO updatedUsuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioException(MessageConstants.USUARIO_NAO_ENCONTRADO_COM_ID + id));
        usuarioMapper.toUpdate(usuario, updatedUsuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioException(MessageConstants.USUARIO_NAO_ENCONTRADO_COM_ID + id);
        }
        usuarioRepository.deleteById(id);
    }
}
