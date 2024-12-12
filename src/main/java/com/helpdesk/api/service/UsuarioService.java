package com.helpdesk.api.service;


import com.helpdesk.api.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsuarioService {
    Usuario createUsuario(Usuario usuario);

    List<Usuario> getAllUsuarios();

    Optional<Usuario> getUsuarioById(Long id);

    Optional<Usuario> updateUsuario(Long id, Usuario updatedUsuario);

    void deleteUsuario(Long id);
}
