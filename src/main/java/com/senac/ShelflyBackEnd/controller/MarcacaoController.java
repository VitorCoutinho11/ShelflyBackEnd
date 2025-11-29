package com.senac.ShelflyBackEnd.controller;

import com.senac.ShelflyBackEnd.dto.request.MarcacaoDTORequest;
import com.senac.ShelflyBackEnd.dto.response.MarcacaoDTOResponse;
import com.senac.ShelflyBackEnd.entity.Livro;
import com.senac.ShelflyBackEnd.entity.Marcacao;
import com.senac.ShelflyBackEnd.service.MarcacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/marcacao")
@Tag(name = "marcacao", description = "API para gerenciamento das marcacoes dos livros")
public class MarcacaoController {

    private MarcacaoService marcacaoService;
    private ModelMapper modelMapper;

    public MarcacaoController(MarcacaoService marcacaoService, ModelMapper modelMapper) {
        this.marcacaoService = marcacaoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/listar")
    @Operation(summary = "listar marcacoes", description = "Endpoint para listar as marcacoes de todos os livros")
    public ResponseEntity<List<Marcacao>> listarMarcacoes() {
        return ResponseEntity.ok(this.marcacaoService.listarMarcacoes());
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova marcação", description = "Endpoint para criar um novo registro de marcação")
    public ResponseEntity<MarcacaoDTOResponse> criarMarcacao(@Valid @RequestBody MarcacaoDTORequest marcacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(marcacaoService.criarMarcacao(marcacao));
    }

    @PutMapping("/atualizar/{marcacaoId}")
    public ResponseEntity<MarcacaoDTOResponse> atualizarMarcacao(
            @PathVariable Integer marcacaoId,
            @RequestBody MarcacaoDTORequest marcacao) {
        return ResponseEntity.ok(marcacaoService.atualizarMarcacao(marcacaoId, marcacao));
    }

    @DeleteMapping("/apagar/{marcacaoId}")
    public ResponseEntity<Void> apagarMarcacao(@PathVariable Integer marcacaoId) {
        marcacaoService.apagarMarcacao(marcacaoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar/{marcacaoId}")
    @Operation(summary = "Listar marcacao por ID", description = "Endpoint para buscar um livro específico pelo seu ID")
    public Marcacao listarMarcacaoPorId(@PathVariable Integer marcacaoId) { // <-- CORRIGIDO: Agora usa 'livroId'
        return marcacaoService.listarPorId(marcacaoId);
    }
}
