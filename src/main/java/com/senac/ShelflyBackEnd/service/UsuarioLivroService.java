package com.senac.ShelflyBackEnd.service;

import com.senac.ShelflyBackEnd.dto.request.UsuarioLivroDTORequest;
import com.senac.ShelflyBackEnd.dto.response.UsuarioLivroDTOResponse;
import com.senac.ShelflyBackEnd.entity.Livro;
import com.senac.ShelflyBackEnd.entity.Usuario;
import com.senac.ShelflyBackEnd.entity.UsuarioLivro;
import com.senac.ShelflyBackEnd.repository.LivroRepository;
import com.senac.ShelflyBackEnd.repository.UsuarioLivroRepository;
import com.senac.ShelflyBackEnd.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class UsuarioLivroService {
    // Injeção de dependências do repositório
    private final UsuarioLivroRepository usuarioLivroRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UsuarioLivroService(UsuarioLivroRepository usuarioLivroRepository,
                               LivroRepository livroRepository,
                               UsuarioRepository usuarioRepository,
                               ModelMapper modelMapper) {
        this.usuarioLivroRepository = usuarioLivroRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public List<UsuarioLivro> listarUsuarioLivros() {
        return this.usuarioLivroRepository.findAll();
    }

    public UsuarioLivroDTOResponse criarUsuarioLivro(UsuarioLivroDTORequest usuarioLivroDTORequest) {
        // 1. Cria a entidade UsuarioLivro manualmente, populando com os dados do DTO.
        UsuarioLivro usuarioLivro = new UsuarioLivro();
        usuarioLivro.setStatus(usuarioLivroDTORequest.getStatus());
        usuarioLivro.setNota(usuarioLivroDTORequest.getNota());
        usuarioLivro.setPaginaAtual(usuarioLivroDTORequest.getPaginaAtual());

        // 2. Busca as entidades Livro e Usuario com base nos IDs fornecidos pelo DTO.
        Livro livro = this.livroRepository.findById(usuarioLivroDTORequest.getLivroId())
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

        Usuario usuario = this.usuarioRepository.findById(usuarioLivroDTORequest.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // 3. Associa as entidades encontradas à entidade UsuarioLivro.
        usuarioLivro.setLivro(livro);
        usuarioLivro.setUsuario(usuario);

        // 4. Define as datas. A data de início é o momento da criação, e a data de fim é nula,
        // já que o usuário ainda não terminou de ler.
        usuarioLivro.setDataInicio(LocalDateTime.now());
        usuarioLivro.setDataFim(null);

        // 5. Salva a nova entidade no banco de dados.
        UsuarioLivro usuarioLivroSalvo = this.usuarioLivroRepository.save(usuarioLivro);

        // 6. Mapeia a entidade salva (agora com o ID) para o DTO de resposta.
        return this.modelMapper.map(usuarioLivroSalvo, UsuarioLivroDTOResponse.class);
    }

    public UsuarioLivroDTOResponse atualizarUsuarioLivro(Integer id, UsuarioLivroDTORequest usuarioLivroDTORequest) {
        // Use a instância do repositório para chamar os métodos
        UsuarioLivro usuarioLivroExistente = this.usuarioLivroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Registro de livro do usuário não encontrado com o ID: " + id));

        if (usuarioLivroDTORequest.getNota() != null) {
            usuarioLivroExistente.setNota(usuarioLivroDTORequest.getNota());
        }
        if (usuarioLivroDTORequest.getStatus() != null) {
            usuarioLivroExistente.setStatus(usuarioLivroDTORequest.getStatus());
        }
        if (usuarioLivroDTORequest.getPaginaAtual() != null) {
            usuarioLivroExistente.setPaginaAtual(usuarioLivroDTORequest.getPaginaAtual());
        }

        if (usuarioLivroDTORequest.getDataFim() != null) {
            usuarioLivroExistente.setDataFim(usuarioLivroDTORequest.getDataFim());
        }

        UsuarioLivro usuarioLivroSalvo = this.usuarioLivroRepository.save(usuarioLivroExistente);

        return this.modelMapper.map(usuarioLivroSalvo, UsuarioLivroDTOResponse.class);
    }

    public void apagarUsuarioLivro(Integer id) {
        usuarioLivroRepository.deleteById(id);
    }

    public UsuarioLivro listarPorId(Integer id) {
        return usuarioLivroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário-Livro não encontrado: " + id));
    }

}


