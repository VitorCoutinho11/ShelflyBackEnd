package com.senac.ShelflyBackEnd.dto;

import com.senac.ShelflyBackEnd.entity.RoleName;

import java.time.LocalDateTime;

public record CreateUserDto(

        String nome,
        String email,
        String password,
        LocalDateTime data,
        Integer status,
        RoleName role
) {
}
