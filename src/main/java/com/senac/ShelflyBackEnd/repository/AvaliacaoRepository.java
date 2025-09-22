package com.senac.ShelflyBackEnd.repository;

import com.senac.ShelflyBackEnd.entity.Avaliacao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Avaliacao a SET a.status = -1 WHERE a.id = :id")
    void apagarAvaliacao (@Param("id")Integer avaliacaoId);

    @Query("SELECT a FROM Avaliacao a WHERE a.status >= 0")
    List<Avaliacao> listarAvaliacoes();
}
