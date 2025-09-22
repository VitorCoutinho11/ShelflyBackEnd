package com.senac.ShelflyBackEnd.repository;

import com.senac.ShelflyBackEnd.entity.Genero;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneroRepository extends JpaRepository <Genero, Integer>{
    @Modifying
    @Transactional
    @Query("UPDATE Genero g SET g.status = -1 WHERE g.id = :id")
    void apagarGenero (@Param("id")Integer generoId);

    @Query("SELECT g FROM Genero g WHERE g.status >= 0")
    List<Genero> listarGeneros();
}
