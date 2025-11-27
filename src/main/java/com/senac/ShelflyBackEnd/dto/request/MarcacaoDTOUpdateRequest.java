package com.senac.ShelflyBackEnd.dto.request;

import com.senac.ShelflyBackEnd.enums.Status;
import com.senac.ShelflyBackEnd.enums.UsuarioLivroStatus;

public class MarcacaoDTOUpdateRequest {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
