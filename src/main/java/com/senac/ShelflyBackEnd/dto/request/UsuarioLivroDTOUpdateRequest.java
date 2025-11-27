package com.senac.ShelflyBackEnd.dto.request;

import com.senac.ShelflyBackEnd.enums.UsuarioLivroStatus;

public class UsuarioLivroDTOUpdateRequest {
    private UsuarioLivroStatus status;

    public UsuarioLivroStatus getStatus() {
        return status;
    }

    public void setStatus(UsuarioLivroStatus status) {
        this.status = status;
    }
}
