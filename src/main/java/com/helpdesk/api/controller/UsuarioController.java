package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.UsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public interface UsuarioController {

    @PostMapping
    ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO);

    @GetMapping
    ResponseEntity<List<UsuarioDTO>> getAllUsuarios();

    @GetMapping("/{id}")
    ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable("id") Long id, @Valid @RequestBody UsuarioDTO updatedUsuarioDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUsuario(@PathVariable("id") Long id);
}
