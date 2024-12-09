package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.UsuarioDTO;
import com.helpdesk.api.exception.UsuarioException;
import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.service.UsuarioService;
import com.helpdesk.api.util.MessageConstants;
import com.helpdesk.api.mapper.UsuarioMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO UsuarioDTO) {
        Usuario usuario = UsuarioMapper.toEntity(UsuarioDTO);
        Usuario novoUsuario = usuarioService.createUsuario(usuario);
        UsuarioDTO novoUsuarioDTO = UsuarioMapper.toDto(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuarioDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        List<UsuarioDTO> usuariosDto = UsuarioMapper.toDtoList(usuarios);
        return ResponseEntity.ok(usuariosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id)
                .orElseThrow(() -> new UsuarioException(MessageConstants.USUARIO_NAO_ENCONTRADO_C0M_ID + id));
        UsuarioDTO UsuarioDTO = UsuarioMapper.toDto(usuario);
        return ResponseEntity.ok(UsuarioDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO updatedUsuarioDTO){
        Usuario updatedUsuario = UsuarioMapper.toEntity(updatedUsuarioDTO);
        Usuario usuarioAtualizado = usuarioService.updateUsuario(id, updatedUsuario);
        UsuarioDTO UsuarioDTOAtualizado = UsuarioMapper.toDto(usuarioAtualizado);
        return ResponseEntity.ok(UsuarioDTOAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
