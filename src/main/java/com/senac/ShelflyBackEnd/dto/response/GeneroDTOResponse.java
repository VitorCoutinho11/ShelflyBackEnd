package com.senac.ShelflyBackEnd.dto.response;

import com.senac.ShelflyBackEnd.enums.Status;

public class GeneroDTOResponse {

    private int id;
    private String nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
