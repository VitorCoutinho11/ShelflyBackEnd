package com.senac.ShelflyBackEnd.dto.request;

import com.senac.ShelflyBackEnd.enums.Status;
import com.senac.ShelflyBackEnd.enums.UsuarioLivroStatus;

public class MarcacaoDTORequest {

    private Integer pagina;
    private String anotacao;
    private int usuarioLivroId;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public int getUsuarioLivroId() {
        return usuarioLivroId;
    }

    public void setUsuarioLivroId(int usuarioLivroId) {
        this.usuarioLivroId = usuarioLivroId;
    }
}
