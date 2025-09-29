package com.senac.ShelflyBackEnd.service;

import com.senac.ShelflyBackEnd.entity.UsuarioRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class UsuarioDetailsImpl {
    private UsuarioRole usuarioRole;

    public UsuarioDetailsImpl(UsuarioRole usuarioRole){
        this.usuarioRole = usuarioRole;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuarioRole.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }
    @Override
    public String getPassword() {
        return usuarioRole.getSenha();
    }
    @Override
    public String getUsername() {
        return usuarioRole.getLogin();
    }
}
