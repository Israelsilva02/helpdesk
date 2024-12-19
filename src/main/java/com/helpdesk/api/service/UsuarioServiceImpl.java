package com.helpdesk.api.service;

import com.helpdesk.api.mapper.UsuarioMapper;
import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.model.dto.UsuarioDTO;
import com.helpdesk.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToUsuarioDTO(savedUsuario);
    }

    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::usuarioToUsuarioDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::usuarioToUsuarioDTO);
    }

    public Optional<UsuarioDTO> updateUsuario(Long id, UsuarioDTO updatedUsuarioDTO) {
        return usuarioRepository.findById(id)
                .map(existingUsuario -> {
                    Usuario updatedUsuario = usuarioMapper.usuarioDTOToUsuario(updatedUsuarioDTO);
                    updatedUsuario.setCustomerId(existingUsuario.getCustomerId());
                    Usuario savedUsuario = usuarioRepository.save(updatedUsuario);
                    return usuarioMapper.usuarioToUsuarioDTO(savedUsuario);
                });
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
