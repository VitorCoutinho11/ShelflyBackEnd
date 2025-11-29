package com.senac.ShelflyBackEnd.Converter;

import com.senac.ShelflyBackEnd.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {

    // Quando salva no DB
    @Override
    public Integer convertToDatabaseColumn(Status status) {
        // Se a Entity tiver status null, salva null no banco
        return status != null ? status.getCodigo() : null;
    }

    // Quando carrega da DB
    @Override
    public Status convertToEntityAttribute(Integer codigo) {
        if (codigo == null) {
            return null; // Se a coluna for null, retorna null para a Entity
        }

        // Delega a busca ao Enum e deixa o Enum lidar com a exceção se o código for inválido
        return Status.fromCodigo(codigo);
    }
}