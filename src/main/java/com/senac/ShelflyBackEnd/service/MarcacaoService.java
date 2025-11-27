package com.senac.ShelflyBackEnd.service;

import com.senac.ShelflyBackEnd.dto.request.MarcacaoDTORequest;
import com.senac.ShelflyBackEnd.dto.response.LivroDTOResponse;
import com.senac.ShelflyBackEnd.dto.response.MarcacaoDTOResponse;
import com.senac.ShelflyBackEnd.dto.response.UsuarioDTOResponse;
import com.senac.ShelflyBackEnd.dto.response.UsuarioLivroDTOResponse;
import com.senac.ShelflyBackEnd.entity.Marcacao;
import com.senac.ShelflyBackEnd.entity.UsuarioLivro;
import com.senac.ShelflyBackEnd.enums.Status; // 🚨 Importe o Enum
import com.senac.ShelflyBackEnd.repository.MarcacaoRepository;
import com.senac.ShelflyBackEnd.repository.UsuarioLivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MarcacaoService {

    private final MarcacaoRepository marcacaoRepository;
    private final UsuarioLivroRepository usuarioLivroRepository;

    @Autowired
    public MarcacaoService(MarcacaoRepository marcacaoRepository,
                           UsuarioLivroRepository usuarioLivroRepository) {
        this.marcacaoRepository = marcacaoRepository;
        this.usuarioLivroRepository = usuarioLivroRepository;
    }

    public List<Marcacao> listarMarcacoes() {
        return this.marcacaoRepository.findAll();
    }

    public MarcacaoDTOResponse criarMarcacao(MarcacaoDTORequest marcacaoDTORequest) {
        UsuarioLivro usuarioLivro = usuarioLivroRepository.findById(marcacaoDTORequest.getUsuarioLivroId())
                .orElseThrow(() -> new IllegalArgumentException("Registro de livro do usuário não encontrado"));

        Marcacao novaMarcacao = new Marcacao();
        novaMarcacao.setUsuarioLivro(usuarioLivro);
        novaMarcacao.setPagina(marcacaoDTORequest.getPagina());
        novaMarcacao.setAnotacao(marcacaoDTORequest.getAnotacao());
        // 🚨 CORREÇÃO: Passa o objeto Enum diretamente
        novaMarcacao.setStatus(marcacaoDTORequest.getStatus());
        novaMarcacao.setData(LocalDateTime.now());

        Marcacao marcacaoSalva = this.marcacaoRepository.save(novaMarcacao);

        return toMarcacaoDTOResponse(marcacaoSalva);
    }

    public MarcacaoDTOResponse atualizarMarcacao(Integer id, MarcacaoDTORequest dto) {
        Marcacao marcacaoExistente = listarPorId(id);

        // 🚨 LÓGICA CORRIGIDA: Comparar com 0 (int) não funciona mais,
        // mas verificar se a página é > 0 é um bom sinal de que o valor deve ser atualizado.
        if (dto.getPagina() != null && dto.getPagina() > 0) { // Adicionando null check para Integer
            marcacaoExistente.setPagina(dto.getPagina());
        }

        // CORREÇÃO: Verifica se o campo de anotação foi enviado (não é nulo e não está vazio)
        if (dto.getAnotacao() != null && !dto.getAnotacao().isBlank()) {
            marcacaoExistente.setAnotacao(dto.getAnotacao());
        }

        // 🚨 LÓGICA CORRIGIDA: Agora verificamos se o Enum foi fornecido no DTO (não é null).
        // Se o DTO tem um status, atualizamos o status existente.
        if (dto.getStatus() != null) {
            marcacaoExistente.setStatus(dto.getStatus());
        }
        // Se a lógica fosse verificar se o status é ATIVA, faríamos:
        // if (dto.getStatus() == EntityStatus.ATIVA) { ... }

        Marcacao marcacaoSalva = marcacaoRepository.save(marcacaoExistente);

        return toMarcacaoDTOResponse(marcacaoSalva);
    }

    // Método auxiliar para mapear a entidade para o DTO de resposta
    private MarcacaoDTOResponse toMarcacaoDTOResponse(Marcacao marcacao) {
        MarcacaoDTOResponse resposta = new MarcacaoDTOResponse();
        resposta.setId(marcacao.getId());
        resposta.setPagina(marcacao.getPagina());
        resposta.setAnotacao(marcacao.getAnotacao());
        resposta.setData(marcacao.getData());


        resposta.setStatus(Status.fromCodigo(marcacao.getStatus().ordinal()));

        // Mapeia o UsuarioLivro, preenchendo todos os campos, incluindo os DTOs aninhados
        UsuarioLivroDTOResponse usuarioLivroDto = new UsuarioLivroDTOResponse();
        usuarioLivroDto.setId(marcacao.getUsuarioLivro().getId());
        usuarioLivroDto.setStatus(marcacao.getUsuarioLivro().getStatus());
        usuarioLivroDto.setNota(marcacao.getUsuarioLivro().getNota());
        usuarioLivroDto.setDataInicio(marcacao.getUsuarioLivro().getDataInicio());
        usuarioLivroDto.setDataFim(marcacao.getUsuarioLivro().getDataFim());
        usuarioLivroDto.setPaginaAtual(marcacao.getUsuarioLivro().getPaginaAtual());

        // Mapeia o Livro e o Usuario para os seus respectivos DTOs aninhados
        LivroDTOResponse livroDto = new LivroDTOResponse();
        livroDto.setId(marcacao.getUsuarioLivro().getLivro().getId());
        // Inclua aqui outros campos do Livro, se necessário

        UsuarioDTOResponse usuarioDto = new UsuarioDTOResponse();
        usuarioDto.setId(marcacao.getUsuarioLivro().getUsuario().getId());
        // Inclua aqui outros campos do Usuario, se necessário

        usuarioLivroDto.setLivro(livroDto);
        usuarioLivroDto.setUsuario(usuarioDto);

        resposta.setUsuarioLivro(usuarioLivroDto);

        return resposta;
    }

    public void apagarMarcacao(Integer id) {
        marcacaoRepository.deleteById(id);
    }

    public Marcacao listarPorId(Integer id) {
        return marcacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Marcação não encontrada: " + id));
    }
}