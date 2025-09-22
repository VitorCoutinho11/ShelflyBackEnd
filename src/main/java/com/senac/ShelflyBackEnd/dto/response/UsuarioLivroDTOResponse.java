package com.senac.ShelflyBackEnd.dto.response;

import java.time.LocalDateTime;

public class UsuarioLivroDTOResponse {

    private int id;
    private Integer status;
    private Double nota;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Integer paginaAtual;
    private UsuarioDTOResponse usuario;
    private LivroDTOResponse livro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(Integer paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public UsuarioDTOResponse getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTOResponse usuario) {
        this.usuario = usuario;
    }

    public LivroDTOResponse getLivro() {
        return livro;
    }

    public void setLivro(LivroDTOResponse livro) {
        this.livro = livro;
    }
}
