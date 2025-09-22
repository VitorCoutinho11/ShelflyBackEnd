package com.senac.ShelflyBackEnd.controller;

import com.senac.ShelflyBackEnd.dto.request.LivroDTORequest;
import com.senac.ShelflyBackEnd.dto.response.LivroDTOResponse;
import com.senac.ShelflyBackEnd.entity.Livro;
import com.senac.ShelflyBackEnd.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/livro")
@Tag(name = "Livro", description = "API para gerenciamento dos livros")
public class LivroController {

    private LivroService livroService;
    private ModelMapper modelMapper;

    public LivroController(LivroService livroService, ModelMapper modelMapper){
        this.livroService = livroService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/listar")
    @Operation(summary = "listar livros", description = "endpoint para listar todos os livros")
    public ResponseEntity<List<Livro>> listarLivros(){
        return ResponseEntity.ok(livroService.listarLivros());
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo livro", description = "Endpoint para criar um novo registro de livro")
    public ResponseEntity<LivroDTOResponse> criarLivro(@Valid @RequestBody LivroDTORequest livro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.criarLivro(livro));
    }

    @PutMapping("/atualizar/{livroId}")
    public ResponseEntity<LivroDTOResponse> atualizarLivro(
            @PathVariable Integer livroId,
            @RequestBody LivroDTORequest livro) {
        return ResponseEntity.ok(livroService.atualizarLivro(livroId, livro));
    }

    @DeleteMapping("/apagar/{livroId}")
    public ResponseEntity<Void> apagarLivro(@PathVariable Integer livroId) {
        livroService.apagarLivro(livroId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ver")
    public ResponseEntity<List<Livro>> verLivros() {
        return ResponseEntity.ok(livroService.listarLivros());
    }
}
