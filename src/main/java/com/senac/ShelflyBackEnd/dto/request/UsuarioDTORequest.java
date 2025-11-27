package com.senac.ShelflyBackEnd.dto.request;

import com.senac.ShelflyBackEnd.enums.Status;
import com.senac.ShelflyBackEnd.enums.UsuarioLivroStatus;

import java.time.LocalDateTime;

public class UsuarioDTORequest {

    private String nome;
    private String email;
    private String senha;
    private LocalDateTime data;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
