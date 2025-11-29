package com.senac.ShelflyBackEnd.service;

import com.senac.ShelflyBackEnd.dto.request.LivroDTORequest;
import com.senac.ShelflyBackEnd.dto.response.LivroDTOResponse;
import com.senac.ShelflyBackEnd.entity.Genero;
import com.senac.ShelflyBackEnd.entity.Livro;
import com.senac.ShelflyBackEnd.repository.GeneroRepository;
import com.senac.ShelflyBackEnd.repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final GeneroRepository generoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public LivroService(LivroRepository livroRepository, GeneroRepository generoRepository) {
        this.livroRepository = livroRepository;
        this.generoRepository = generoRepository;
    }

    public List<Livro> listarLivros() {
        return this.livroRepository.findAll();
    }

    public LivroDTOResponse criarLivro(LivroDTORequest livroDTORequest) {
        Livro livro = new Livro();

        // 1. Mapear manualmente os campos do DTO para a entidade
        livro.setTitulo(livroDTORequest.getTitulo());
        livro.setAutor(livroDTORequest.getAutor());
        livro.setPublicacao(livroDTORequest.getPublicacao());
        livro.setDescricao(livroDTORequest.getDescricao());
        livro.setCapa(livroDTORequest.getCapa());
        livro.setStatus(livroDTORequest.getStatus());
        livro.setDataCadastro(LocalDateTime.now());

        // 2. Buscar e associar o gênero (como você já estava fazendo)
        if (livroDTORequest.getGeneroId() != null) {
            Genero genero = generoRepository.findById(livroDTORequest.getGeneroId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Gênero não encontrado: " + livroDTORequest.getGeneroId()));
            livro.setGenero(genero);
        }

        // 3. Salvar o novo livro
        Livro livroSave = this.livroRepository.save(livro);

        // 4. Mapear de volta para o DTO de resposta
        return this.modelMapper.map(livroSave, LivroDTOResponse.class);
    }

    public LivroDTOResponse atualizarLivro(Integer id, LivroDTORequest dto) {
        // 1. Encontrar o Livro existente pelo ID
        Livro livroExistente = buscarLivroPorId(id);

        // 2. Mapear os campos do DTO para a entidade manualmente
        if (dto.getTitulo() != null) {
            livroExistente.setTitulo(dto.getTitulo());
        }
        if (dto.getAutor() != null) {
            livroExistente.setAutor(dto.getAutor());
        }
        if (dto.getPublicacao() != null) {
            livroExistente.setPublicacao(dto.getPublicacao());
        }
        if (dto.getDescricao() != null) {
            livroExistente.setDescricao(dto.getDescricao());
        }
        if (dto.getCapa() != null) {
            livroExistente.setCapa(dto.getCapa());
        }
        if (dto.getStatus() != null) {
            livroExistente.setStatus(dto.getStatus());
        }


        // 3. Atualizar o gênero caso tenha vindo no DTO
        if (dto.getGeneroId() != null) {
            Genero genero = generoRepository.findById(dto.getGeneroId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Gênero não encontrado: " + dto.getGeneroId()));
            livroExistente.setGenero(genero);
        }

        // 4. Salvar a entidade EXISTENTE, o Hibernate fará o UPDATE
        Livro livroSalvo = livroRepository.save(livroExistente);

        // 5. Mapear de volta para o DTO de resposta
        // Você pode fazer isso manualmente também, se quiser
        return this.modelMapper.map(livroSalvo, LivroDTOResponse.class);
    }

    public void apagarLivro(Integer id) {
        // aqui você pode decidir se será delete físico ou soft delete
        livroRepository.deleteById(id);
    }

    public Livro buscarLivroPorId(Integer id) {
        // Usa o .findById do Spring Data JPA, que retorna um Optional.
        return livroRepository.findById(id)
                // Se o livro não existir, lança uma exceção com status 404 NOT FOUND.
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Livro não encontrado com o ID: " + id
                ));
    }


}
