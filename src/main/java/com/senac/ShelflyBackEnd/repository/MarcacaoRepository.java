package com.senac.ShelflyBackEnd.repository;

import com.senac.ShelflyBackEnd.entity.Marcacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcacaoRepository extends JpaRepository <Marcacao, Integer> {
}
