package com.senac.ShelflyBackEnd.entity;

import com.senac.ShelflyBackEnd.enums.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "genero_livro")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genero_livro_id")
    private int id;

    @Column(name = "genero_livro_nome")
    private String nome;

    @Column(name = "genero_status")
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
