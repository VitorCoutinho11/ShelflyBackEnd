package com.senac.ShelflyBackEnd.service;


import com.senac.ShelflyBackEnd.dto.request.UsuarioDTORequest;
import com.senac.ShelflyBackEnd.dto.response.UsuarioDTOResponse;
import com.senac.ShelflyBackEnd.entity.Usuario;
import com.senac.ShelflyBackEnd.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios(){
        return this.usuarioRepository.findAll();
    }

    public UsuarioDTOResponse criarUsuario(UsuarioDTORequest usuarioDTORequest) {
        Usuario usuario = this.modelMapper.map(usuarioDTORequest, Usuario.class);
        Usuario usuarioSave = this.usuarioRepository.save(usuario);
        UsuarioDTOResponse usuarioDTOResponse = this.modelMapper.map(usuarioSave, UsuarioDTOResponse.class);
        return usuarioDTOResponse;
    }

    public UsuarioDTOResponse atualizarUsuario(Integer id, UsuarioDTORequest dto) {
        Usuario usuario = listarPorId(id);
        modelMapper.map(dto, usuario);
        Usuario usuarioSave = usuarioRepository.save(usuario);
        return modelMapper.map(usuarioSave, UsuarioDTOResponse.class);
    }

    public void apagarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario listarPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));
    }
}
