package com.senac.ShelflyBackEnd.dto;

import com.senac.ShelflyBackEnd.entity.Role;

import java.util.List;

public record RecoveryUserDto(
        Long id,
        String email,
        List<Role> roles
) {
}
