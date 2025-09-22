package com.senac.ShelflyBackEnd.dto.request;

public class MarcacaoDTORequest {

    private Integer pagina;
    private String anotacao;
    private int usuarioLivroId;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
