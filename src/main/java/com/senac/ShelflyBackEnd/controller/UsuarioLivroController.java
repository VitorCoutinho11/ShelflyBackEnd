package com.senac.ShelflyBackEnd.controller;

import com.senac.ShelflyBackEnd.dto.request.UsuarioLivroDTORequest;
import com.senac.ShelflyBackEnd.dto.response.UsuarioLivroDTOResponse;
import com.senac.ShelflyBackEnd.entity.Livro;
import com.senac.ShelflyBackEnd.entity.UsuarioLivro;
import com.senac.ShelflyBackEnd.service.UsuarioLivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios-livros")
@Tag(name = "UsuarioLivro", description = "API para gerenciamento dos livros do usuário")
public class UsuarioLivroController {

    private UsuarioLivroService usuarioLivroService;
    private ModelMapper modelMapper;

    public UsuarioLivroController(UsuarioLivroService usuarioLivroService, ModelMapper modelMapper){
        this.usuarioLivroService = usuarioLivroService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar livros do usuário", description = "Endpoint para listar todos os livros do usuário")
    public ResponseEntity<List<UsuarioLivro>> listarUsuarioLivros() {
        return ResponseEntity.ok(usuarioLivroService.listarUsuarioLivros());
    }

    @PostMapping("/criar")
    @Operation(summary = "Associar livro a usuário", description = "Endpoint para criar relação usuario-livro")
    public ResponseEntity<UsuarioLivroDTOResponse> criarUsuarioLivro(@Valid @RequestBody UsuarioLivroDTORequest usuarioLivro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioLivroService.criarUsuarioLivro(usuarioLivro));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioLivroDTOResponse> atualizarUsuarioLivro(
            @PathVariable Integer id,
            @RequestBody UsuarioLivroDTORequest dto) {
        return ResponseEntity.ok(usuarioLivroService.atualizarUsuarioLivro(id, dto));
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> apagarUsuarioLivro(@PathVariable Integer id) {
        usuarioLivroService.apagarUsuarioLivro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Listar usuariolivro por ID", description = "Endpoint para buscar um livro específico pelo seu ID")
    public UsuarioLivro listarUsuarioLivroPorId(@PathVariable Integer id) { // <-- CORRIGIDO: Agora usa 'livroId'
        return usuarioLivroService.listarPorId(id);
    }
}
