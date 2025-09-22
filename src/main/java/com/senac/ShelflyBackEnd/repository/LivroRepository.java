package com.senac.ShelflyBackEnd.repository;

import com.senac.ShelflyBackEnd.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository <Livro, Integer> {
}
