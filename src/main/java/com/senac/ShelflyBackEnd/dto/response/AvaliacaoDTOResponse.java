package com.senac.ShelflyBackEnd.dto.response;

import com.senac.ShelflyBackEnd.enums.Status;
import com.senac.ShelflyBackEnd.enums.UsuarioLivroStatus;

import java.time.LocalDateTime;

public class AvaliacaoDTOResponse {

    private Long id;
    private Float nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;
    private UsuarioDTOResponse usuario;
    private LivroDTOResponse livro;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
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
