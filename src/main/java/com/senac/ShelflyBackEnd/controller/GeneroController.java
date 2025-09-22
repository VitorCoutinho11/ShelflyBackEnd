package com.senac.ShelflyBackEnd.controller;

import com.senac.ShelflyBackEnd.dto.request.GeneroDTORequest;
import com.senac.ShelflyBackEnd.dto.response.GeneroDTOResponse;
import com.senac.ShelflyBackEnd.entity.Genero;
import com.senac.ShelflyBackEnd.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/genero")
@Tag(name = "Genero", description = "API para gerenciamento de generos dos livros")
public class GeneroController {

    private GeneroService generoService;
    private ModelMapper modelMapper;

    public  GeneroController(GeneroService generoService, ModelMapper modelMapper){
        this.generoService = generoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar generos", description = "Endpoint para listar todos os generos de livros")
    public ResponseEntity<List<Genero>> listarGeneros() {
        return ResponseEntity.ok(generoService.listarGeneros());
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo gênero", description = "Endpoint para criar um novo registro de gênero")
    public ResponseEntity<GeneroDTOResponse> criarGenero(@Valid @RequestBody GeneroDTORequest genero) {
        return ResponseEntity.status(HttpStatus.CREATED).body(generoService.criarGenero(genero));
    }

    @PutMapping("/atualizar/{generoId}")
    public ResponseEntity<GeneroDTOResponse> atualizarGenero(
            @PathVariable Integer generoId,
            @RequestBody GeneroDTORequest genero) {
        return ResponseEntity.ok(generoService.atualizarGenero(generoId, genero));
    }

    @DeleteMapping("/apagar/{generoId}")
    public ResponseEntity<Void> apagarGenero(@PathVariable Integer generoId) {
        generoService.apagarGenero(generoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ver")
    public ResponseEntity<List<Genero>> verGeneros() {
        return ResponseEntity.ok(generoService.listarGeneros());
    }
}
