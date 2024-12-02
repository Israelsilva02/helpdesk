package com.helpdesk.api.service;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario createUsuario(Usuario usuario);
    List<Usuario> getAllUsuarios();
    Optional<Usuario> getUsuarioById(Long id);
    Usuario updateUsuario(Long id, Usuario updatedUsuario) throws BalcaoException;
    void deleteUsuario(Long id);
}