package com.senac.ShelflyBackEnd.dto.response;

import com.senac.ShelflyBackEnd.enums.Status;
import java.time.LocalDateTime;

/**
 * DTO de Resposta para a entidade Marcacao.
 * Usa o objeto Enum Status diretamente.
 */
public class MarcacaoDTOResponse {

    private Integer id;
    private Integer pagina;
    private String anotacao;
    private LocalDateTime data;

    // 🚨 CORREÇÃO: O DTO de Resposta agora espera o objeto Enum Status
    private Status status;

    private UsuarioLivroDTOResponse usuarioLivro; // Relacionamento

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UsuarioLivroDTOResponse getUsuarioLivro() {
        return usuarioLivro;
    }

    public void setUsuarioLivro(UsuarioLivroDTOResponse usuarioLivro) {
        this.usuarioLivro = usuarioLivro;
    }
}