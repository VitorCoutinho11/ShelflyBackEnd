package com.senac.ShelflyBackEnd.dto.response;

import com.senac.ShelflyBackEnd.enums.UsuarioLivroStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class UsuarioLivroDTOUpdateResponse {
    @NotEmpty
    private int id;
    @NotEmpty
    @Min(0)
    @Max(2)
    private UsuarioLivroStatus status;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public UsuarioLivroStatus getStatus() {
        return status;
    }

    public void setStatus(UsuarioLivroStatus status) {
        this.status = status;
    }
}
