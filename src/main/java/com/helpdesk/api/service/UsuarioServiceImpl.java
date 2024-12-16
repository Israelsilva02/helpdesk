package com.helpdesk.api.service;

import com.helpdesk.api.mapper.UsuarioMapper;
import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.model.dto.UsuarioDTO;
import com.helpdesk.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.helpdesk.api.mapper.UsuarioMapper.*;

@Service
public class UsuarioServiceImpl{

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = toEntityUsuario(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return toDtoUsuarioDto(savedUsuario);
    }
    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return toDtoUsuario(usuarios);
    }
    public Optional<UsuarioDTO> getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toDtoUsuarioDto);
    }
    public Optional<UsuarioDTO> updateUsuario(Long id, UsuarioDTO updatedUsuarioDTO) {
        return usuarioRepository.findById(id)
                .map(existingUsuario -> {
                    Usuario updatedUsuario = toEntityUsuario(updatedUsuarioDTO);
                    updatedUsuario.setCustomerId(existingUsuario.getCustomerId());
                    Usuario savedUsuario = usuarioRepository.save(updatedUsuario);
                    return toDtoUsuarioDto(savedUsuario);
                });
    }
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
