package com.senac.ShelflyBackEnd.dto.request;

import com.senac.ShelflyBackEnd.enums.Status;

public class AvaliacaoDTOUpdateRequest {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
