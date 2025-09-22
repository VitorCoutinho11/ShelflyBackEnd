package com.senac.ShelflyBackEnd.controller;

import com.senac.ShelflyBackEnd.dto.request.UsuarioDTORequest;
import com.senac.ShelflyBackEnd.dto.response.UsuarioDTOResponse;
import com.senac.ShelflyBackEnd.entity.Usuario;
import com.senac.ShelflyBackEnd.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
@Tag(name = "Usuario", description = "API para gerenciamento de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private ModelMapper modelMapper;

    public UsuarioController(UsuarioService usuarioService, ModelMapper modelMapper){
        this.usuarioService = usuarioService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/listar")
    @Operation(summary = "listar Usuarios", description = "endpoint para listar todos os usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios () {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo usuário", description = "Endpoint para criar um novo registro de usuário")
    public ResponseEntity<UsuarioDTOResponse> criarUsuario(@Valid @RequestBody UsuarioDTORequest usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(usuario));
    }

    @PutMapping("/atualizar/{usuarioId}")
    public ResponseEntity<UsuarioDTOResponse> atualizarUsuario(
            @PathVariable Integer usuarioId,
            @RequestBody UsuarioDTORequest usuario) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(usuarioId, usuario));
    }

    @DeleteMapping("/apagar/{usuarioId}")
    public ResponseEntity<Void> apagarUsuario(@PathVariable Integer usuarioId) {
        usuarioService.apagarUsuario(usuarioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ver")
    public ResponseEntity<List<Usuario>> verUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }
}
