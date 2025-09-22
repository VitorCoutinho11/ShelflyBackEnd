package com.senac.ShelflyBackEnd.dto.response;

import java.time.LocalDateTime;

public class MarcacaoDTOResponse {

    private int id;
    private int pagina;
    private String anotacao;
    private LocalDateTime data;
    private UsuarioLivroDTOResponse usuarioLivro;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
