package com.senac.ShelflyBackEnd.repository;

import com.senac.ShelflyBackEnd.entity.UsuarioRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRoleRepository extends JpaRepository<UsuarioRole, Integer> {
    Optional<UsuarioRole> findByLogin(String login);
}
