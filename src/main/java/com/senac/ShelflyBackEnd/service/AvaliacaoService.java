package com.senac.ShelflyBackEnd.service;

import com.senac.ShelflyBackEnd.dto.request.AvaliacaoDTORequest;
import com.senac.ShelflyBackEnd.dto.response.AvaliacaoDTOResponse;
import com.senac.ShelflyBackEnd.entity.Avaliacao;
import com.senac.ShelflyBackEnd.entity.Livro;
import com.senac.ShelflyBackEnd.entity.Usuario;
import com.senac.ShelflyBackEnd.repository.AvaliacaoRepository;
import com.senac.ShelflyBackEnd.repository.LivroRepository;
import com.senac.ShelflyBackEnd.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvaliacaoService {

    private AvaliacaoRepository avaliacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;
    @Autowired
    private ModelMapper modelMapper;
    public  AvaliacaoService(AvaliacaoRepository avaliacaoRepository, ModelMapper modelMapper, LivroRepository livroRepository, UsuarioRepository usuarioRepository){
        this.avaliacaoRepository = avaliacaoRepository;
        this.modelMapper = modelMapper;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Avaliacao> listarAvaliacoes(){
        return this.avaliacaoRepository.findAll();
    }

    public AvaliacaoDTOResponse criarAvaliacao(AvaliacaoDTORequest avaliacaoDTORequest) {
        // 1. Criar a entidade Avaliacao manualmente para evitar o conflito do ModelMapper.
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoDTORequest.getNota());
        avaliacao.setComentario(avaliacaoDTORequest.getComentario());

        // 2. Buscar as entidades relacionadas (Livro e Usuario) com base nos IDs do DTO
        Livro livro = this.livroRepository.findById(avaliacaoDTORequest.getLivroId())
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

        Usuario usuario = this.usuarioRepository.findById(avaliacaoDTORequest.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // 3. Associar as entidades à Avaliação
        avaliacao.setLivro(livro);
        avaliacao.setUsuario(usuario);
        avaliacao.setDataAvaliacao(LocalDateTime.now());

        // 4. Salvar a entidade no banco de dados.
        Avaliacao avaliacaoSalva = this.avaliacaoRepository.save(avaliacao);

        // 5. Mapear a entidade SALVA para o DTO de resposta.
        // Isso garante que o ID e outros campos gerados estejam no DTO.
        return this.modelMapper.map(avaliacaoSalva, AvaliacaoDTOResponse.class);
    }

    public AvaliacaoDTOResponse atualizarAvaliacao(Integer id, AvaliacaoDTORequest avaliacaoDTORequest) {
        // 1. Encontrar a Avaliação existente pelo ID
        Avaliacao avaliacaoExistente = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada com o ID: " + id));

        // 2. Mapear os campos do DTO para a entidade manualmente
        // Apenas atualize os campos que podem ser alterados, como a nota e o comentário.
        if (avaliacaoDTORequest.getNota() != null) {
            avaliacaoExistente.setNota(avaliacaoDTORequest.getNota());
        }
        if (avaliacaoDTORequest.getComentario() != null) {
            avaliacaoExistente.setComentario(avaliacaoDTORequest.getComentario());
        }

        // 3. Salvar a entidade EXISTENTE.
        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacaoExistente);

        // 4. Mapear a entidade salva para o DTO de resposta
        return modelMapper.map(avaliacaoSalva, AvaliacaoDTOResponse.class);
    }

    public Avaliacao listarPorId(Integer id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Avaliação não encontrada: " + id));
    }

    public void apagarAvaliacao(Integer id) {
        avaliacaoRepository.deleteById(id);
    }
}
