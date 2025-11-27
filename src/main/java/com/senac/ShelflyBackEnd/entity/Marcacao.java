package com.senac.ShelflyBackEnd.entity;

import com.senac.ShelflyBackEnd.enums.Status;
import com.senac.ShelflyBackEnd.enums.UsuarioLivroStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Marcacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marcacao_id")
    private int id;

    @Column(name = "marcacao_pagina")
    private int pagina;

    @Column(name = "marcacao_anotacao", columnDefinition = "TEXT")
    private String anotacao;

    @Column(name = "marcacao_data")
    private LocalDateTime data;

    @OneToOne
    @JoinColumn(name = "usuario_livro_id")
    private UsuarioLivro usuarioLivro;

    @Column(name = "marcacao_status")
    private Status status;

    @Column(name = "marcacao_leitura")
    private int leitura;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getLeitura() {
        return leitura;
    }

    public void setLeitura(int leitura) {
        this.leitura = leitura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
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

    public UsuarioLivro getUsuarioLivro() {
        return usuarioLivro;
    }

    public void setUsuarioLivro(UsuarioLivro usuarioLivro) {
        this.usuarioLivro = usuarioLivro;
    }
}
