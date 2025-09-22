package com.senac.ShelflyBackEnd.repository;

import com.senac.ShelflyBackEnd.entity.UsuarioLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioLivroRepository extends JpaRepository <UsuarioLivro, Integer> {
}
