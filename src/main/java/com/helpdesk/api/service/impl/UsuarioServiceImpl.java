package com.helpdesk.api.service.impl;

import com.helpdesk.api.exception.UsuarioException;
import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.repositorio.UsuarioRepository;
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
    public Usuario createUsuario(Usuario usuario) throws UsuarioException {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new UsuarioException(MessageConstants.OCORREU_UM_ERRO_AO_CRIAR_USUARIO, e);
        }
    }

    @Override
    public List<Usuario> getAllUsuarios() throws UsuarioException {
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            throw new UsuarioException(MessageConstants.OCORREU_UM_ERRO_AO_OBTER_TODOS_OS_USUARIOS, e);
        }
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) throws UsuarioException {
        try {
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

            if (optionalUsuario.isPresent()) {
                return optionalUsuario;
            } else {
                throw new UsuarioException(MessageConstants.USUARIO_NAO_ENCONTRADO_C0M_ID + id);
            }
        } catch (Exception e) {
            throw new UsuarioException(MessageConstants.OCORREU_UM_ERRO_AO_OBTER_O_USUARIO_C0M_ID + id, e);
        }
    }

    @Override
    public Usuario updateUsuario(Long id, Usuario updatedUsuario) throws UsuarioException {
        try {
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

            if (optionalUsuario.isPresent()) {
                Usuario usuarioExistente = optionalUsuario.get();

                usuarioExistente.setNome(updatedUsuario.getNome());
                usuarioExistente.setEmail(updatedUsuario.getEmail());

                return usuarioRepository.save(usuarioExistente);
            } else {
                throw new UsuarioException(MessageConstants.USUARIO_NAO_ENCONTRADO_C0M_ID + id);
            }
        } catch (Exception e) {
            throw new UsuarioException(MessageConstants.OCORREU_UM_ERRO_AO_ATUALIZAR_O_USUARIO_COM_ID + id, e);
        }
    }

    @Override
    public void deleteUsuario(Long id) throws UsuarioException {
        try {
            if (usuarioRepository.existsById(id)) {
                usuarioRepository.deleteById(id);
            } else {
                throw new UsuarioException(MessageConstants.USUARIO_NAO_ENCONTRADO_C0M_ID + id);
            }
        } catch (Exception e) {
            throw new UsuarioException(MessageConstants.OCORREU_UM_ERRO_AO_DELETAR_O_USUARIO_COM_ID + id, e);
        }
    }
}