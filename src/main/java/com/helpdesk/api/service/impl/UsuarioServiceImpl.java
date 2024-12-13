package com.helpdesk.api.service.impl;

import com.helpdesk.api.exception.UsuarioException;
import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.repository.UsuarioRepository;
import com.helpdesk.api.service.UsuarioService;
import com.helpdesk.api.util.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        if (usuario.getCustomerId() == null || usuarioRepository.findById(usuario.getCustomerId()).isEmpty()) {
            return usuarioRepository.save(usuario);
        }
        throw new UsuarioException(MessageConstants.USUARIO_COM_ESSE_ID_JA_EXISTE + usuario.getCustomerId());
    }


    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }


    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            return optionalUsuario;
        } else {
            throw new UsuarioException(MessageConstants.USUARIO_NAO_ENCONTRADO_C0M_ID + id);
        }
    }

    @Override
    public Optional<Usuario> updateUsuario(Long id, Usuario updatedUsuario)  {

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuarioExistente = optionalUsuario.get();
            usuarioExistente.setNome(updatedUsuario.getNome());
            usuarioExistente.setEmail(updatedUsuario.getEmail());

            return Optional.of(usuarioRepository.save(usuarioExistente));
        }
        throw new UsuarioException(MessageConstants.USUARIO_NAO_ENCONTRADO_C0M_ID + id);

    }


    @Override
    public void deleteUsuario(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            throw new UsuarioException(MessageConstants.USUARIO_NAO_ENCONTRADO_C0M_ID + id);
        }
        usuarioRepository.deleteById(id);
    }
}
