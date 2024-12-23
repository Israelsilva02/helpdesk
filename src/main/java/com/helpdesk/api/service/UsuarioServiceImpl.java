package com.helpdesk.api.service;

import com.helpdesk.api.exception.UsuarioException;
import com.helpdesk.api.mapper.UsuarioMapper;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.model.dto.BalcaoDTO;
import com.helpdesk.api.model.dto.UsuarioDTO;
import com.helpdesk.api.repository.UsuarioRepository;
import com.helpdesk.api.util.MessageConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.helpdesk.api.mapper.UsuarioMapper.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl{

    private final UsuarioRepository usuarioRepository;

    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = INSTANCE.toEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return INSTANCE.toDTO(savedUsuario);
    }
    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return INSTANCE.usuariosToUsuarioDTO(usuarios);
    }
    public UsuarioDTO getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new UsuarioException(MessageConstants.USUARIO_NAO_ENCONTRADO_COM_ID + id));
        return INSTANCE.toDTO(usuario);
    }
    public Optional<UsuarioDTO> updateUsuario(Long id, UsuarioDTO updatedUsuarioDTO) {
        return usuarioRepository.findById(id)
                .map(existingUsuario -> {
                    Usuario updatedUsuario = INSTANCE.toEntity(updatedUsuarioDTO);
                    updatedUsuario.setCustomerId(existingUsuario.getCustomerId());
                    Usuario savedUsuario = usuarioRepository.save(updatedUsuario);
                    return INSTANCE.toDTO(savedUsuario);
                });
    }
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
