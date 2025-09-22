package com.senac.ShelflyBackEnd.dto.request;

public class GeneroDTORequest {

    private String nome;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
