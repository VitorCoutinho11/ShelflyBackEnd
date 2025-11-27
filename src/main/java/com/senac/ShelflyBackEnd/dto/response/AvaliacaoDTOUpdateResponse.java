package com.senac.ShelflyBackEnd.dto.response;

import com.senac.ShelflyBackEnd.enums.Status;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class AvaliacaoDTOUpdateResponse {
    @NotEmpty
    private int id;
    @NotEmpty
    @Min(0)
    @Max(2)
    private Status status;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
