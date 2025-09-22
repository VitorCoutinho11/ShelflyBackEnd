package com.senac.ShelflyBackEnd.entity;

import com.senac.ShelflyBackEnd.entity.Genero;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "livro_id")
    private Integer id;

    @Column(name = "livro_titulo")
    private String titulo;

    @Column(name = "livro_autor")
    private String autor;

    @Column(name = "livro_ano_publicacao")
    private Integer publicacao;

    @Column(name = "livro_descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "livro_capa")
    private String capa;

    @Column(name = "livro_data_cadastro")
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "genero_livro_id", nullable = false)
    private Genero genero;

    @Column(name = "livro_status")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(int publicacao) {
        this.publicacao = publicacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}