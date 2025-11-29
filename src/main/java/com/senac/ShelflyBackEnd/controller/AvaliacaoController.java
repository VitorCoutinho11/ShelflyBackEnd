package com.senac.ShelflyBackEnd.controller;

import com.senac.ShelflyBackEnd.dto.request.AvaliacaoDTORequest;
import com.senac.ShelflyBackEnd.dto.response.AvaliacaoDTOResponse;
import com.senac.ShelflyBackEnd.entity.Avaliacao;
import com.senac.ShelflyBackEnd.entity.Livro;
import com.senac.ShelflyBackEnd.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/avaliacao")
@Tag(name = "Avaliacao", description = "API para gerenciamento de Avaliações")
public class AvaliacaoController {

    private AvaliacaoService avaliacaoService;
    private ModelMapper modelMapper;

    public AvaliacaoController(AvaliacaoService avaliacaoService, ModelMapper modelMapper){
        this.avaliacaoService = avaliacaoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/listar")
    @Operation(summary = "listar Avaliações", description = "endpoint para listar todos as avaliações")
    public ResponseEntity<List<Avaliacao>> listarAvaliacoes () {
        return ResponseEntity.ok(avaliacaoService.listarAvaliacoes());
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova avaliacao", description = "Endpoint para criar um novo registro de avaliacao")
    public ResponseEntity<AvaliacaoDTOResponse> criarAvaliacao(@Valid @RequestBody AvaliacaoDTORequest avaliacao){
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.criarAvaliacao(avaliacao));
    }

    @PutMapping("/atualizar/{avaliacaoId}")
    public ResponseEntity<AvaliacaoDTOResponse> atualizarAvaliacao(
            @PathVariable Integer avaliacaoId,
            @RequestBody AvaliacaoDTORequest avaliacao) {
        return ResponseEntity.ok(avaliacaoService.atualizarAvaliacao(avaliacaoId, avaliacao));
    }

    @DeleteMapping("/apagar/{avaliacaoId}")
    public ResponseEntity<Void> apagarAvaliacao(@PathVariable Integer avaliacaoId) {
        avaliacaoService.apagarAvaliacao(avaliacaoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar/{avaliacaoId}")
    @Operation(summary = "Listar avaliacao por ID", description = "Endpoint para buscar um livro específico pelo seu ID")
    public Avaliacao listarAvaliacaoPorId(@PathVariable Integer avaliacaoId) { // <-- CORRIGIDO: Agora usa 'livroId'
        return avaliacaoService.listarPorId(avaliacaoId);
    }
}
