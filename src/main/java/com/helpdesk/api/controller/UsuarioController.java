package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.UsuarioDTO;
import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.helpdesk.api.mapper.UsuarioMapper.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.createUsuario(toEntityUsuario(usuarioDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(toDtoUsuario(usuarios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        return usuario.map(value ->ResponseEntity.ok(toDtoUsuarioDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable ("id")Long id,@Valid @RequestBody UsuarioDTO updatedUsuarioDTO) {
        Optional<Usuario> usuario = usuarioService.updateUsuario(id,toEntityUsuario(updatedUsuarioDTO));
          if(usuario.isEmpty()){
              return ResponseEntity.notFound().build();
          }
          return ResponseEntity.ok(updatedUsuarioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
