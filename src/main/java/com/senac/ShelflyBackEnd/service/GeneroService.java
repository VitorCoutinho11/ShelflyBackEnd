package com.senac.ShelflyBackEnd.service;

import com.senac.ShelflyBackEnd.dto.request.GeneroDTORequest;
import com.senac.ShelflyBackEnd.dto.response.GeneroDTOResponse;
import com.senac.ShelflyBackEnd.entity.Genero;
import com.senac.ShelflyBackEnd.entity.Usuario;
import com.senac.ShelflyBackEnd.repository.GeneroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    private GeneroRepository generoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public GeneroService(GeneroRepository generoRepository){
        this.generoRepository = generoRepository;
    }

    public List<Genero> listarGeneros(){return this.generoRepository.findAll();}

    public GeneroDTOResponse criarGenero(GeneroDTORequest generoDTORequest) {
        Genero genero = this.modelMapper.map(generoDTORequest, Genero.class);
        Genero generoSave = this.generoRepository.save(genero);
        GeneroDTOResponse generoDTOResponse = this.modelMapper.map(generoSave, GeneroDTOResponse.class);
        return generoDTOResponse;
    }

    public GeneroDTOResponse atualizarGenero(Integer id, GeneroDTORequest dto) {
        Genero genero = listarPorId(id);
        modelMapper.map(dto, genero);
        Genero generoSave = generoRepository.save(genero);
        return modelMapper.map(generoSave, GeneroDTOResponse.class);
    }

    public void apagarGenero(Integer id) {
        generoRepository.deleteById(id);
    }

    public Genero listarPorId(Integer id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gênero não encontrado: " + id));
    }
}
