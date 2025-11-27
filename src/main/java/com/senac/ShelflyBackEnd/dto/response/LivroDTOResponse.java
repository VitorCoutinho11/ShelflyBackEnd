package com.senac.ShelflyBackEnd.dto.response;

import com.senac.ShelflyBackEnd.enums.Status;

import java.time.LocalDateTime;

public class LivroDTOResponse {

    private int id;
    private String titulo;
    private String autor;
    private Integer publicacao;
    private String descricao;
    private String capa;
    private LocalDateTime dataCadastro;
    private GeneroDTOResponse genero;
    private Status status;

    public Integer getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Integer publicacao) {
        this.publicacao = publicacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public GeneroDTOResponse getGenero() {
        return genero;
    }

    public void setGenero(GeneroDTOResponse genero) {
        this.genero = genero;
    }
}
