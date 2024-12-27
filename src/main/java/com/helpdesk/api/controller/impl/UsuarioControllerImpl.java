package com.helpdesk.api.controller.impl;

import com.helpdesk.api.controller.UsuarioController;
import com.helpdesk.api.model.dto.UsuarioDTO;
import com.helpdesk.api.service.UsuarioServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioControllerImpl implements UsuarioController {

    private final UsuarioServiceImpl usuarioServiceImpl;

    @Override
    public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO createdUsuario = usuarioServiceImpl.createUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
    }

    @Override
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioServiceImpl.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @Override
    public ResponseEntity<UsuarioDTO> getUsuarioById(Long id) {
        UsuarioDTO usuario = usuarioServiceImpl.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity<UsuarioDTO> updateUsuario(Long id, @Valid @RequestBody UsuarioDTO updatedUsuarioDTO) {
      UsuarioDTO usuario = usuarioServiceImpl.updateUsuario(id, updatedUsuarioDTO);
      return ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity<Void> deleteUsuario(Long id) {
        usuarioServiceImpl.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
