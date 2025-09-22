package com.senac.ShelflyBackEnd.dto.request;

import java.time.LocalDateTime;

public class LivroDTORequest {

    private String titulo;
    private String autor;
    private Integer publicacao;
    private String descricao;
    private String capa;
    private Integer generoId; // <-- mantém como Integer (pode ser null)
    private Integer status;
    private LocalDateTime dataCadastro;

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Integer publicacao) {
        this.publicacao = publicacao;
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

    public Integer getGeneroId() {   // <-- agora bate com o atributo
        return generoId;
    }

    public void setGeneroId(Integer generoId) { // <-- aqui também
        this.generoId = generoId;
    }
}
