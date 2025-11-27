package com.senac.ShelflyBackEnd.dto.response;

import com.senac.ShelflyBackEnd.enums.Status;

import java.time.LocalDateTime;

public class MarcacaoDTOResponse {

    private int id;
    private int pagina;
    private String anotacao;
    private LocalDateTime data;
    private UsuarioLivroDTOResponse usuarioLivro;
    private Status status;

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

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public UsuarioLivroDTOResponse getUsuarioLivro() {
        return usuarioLivro;
    }

    public void setUsuarioLivro(UsuarioLivroDTOResponse usuarioLivro) {
        this.usuarioLivro = usuarioLivro;
    }
}
