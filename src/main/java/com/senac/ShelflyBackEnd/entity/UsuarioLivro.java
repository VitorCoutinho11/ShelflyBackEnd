package com.senac.ShelflyBackEnd.entity;

import com.senac.ShelflyBackEnd.enums.Status;
import com.senac.ShelflyBackEnd.enums.UsuarioLivroStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class UsuarioLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_livro_id")
    private int id;

    @Column(name = "usuario_livro_status")
    private UsuarioLivroStatus status;
    @Column(name = "usuario_livro_nota")
    private double nota;
    @Column(name = "usuario_livro_data_inicio")
    private LocalDateTime dataInicio;
    @Column(name = "usuario_livro_data_fim")
    private LocalDateTime dataFim;
    @Column(name = "usuario_livro_pagina_atual")
    private Integer paginaAtual;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

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

    public void setPaginaAtual(Integer paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
