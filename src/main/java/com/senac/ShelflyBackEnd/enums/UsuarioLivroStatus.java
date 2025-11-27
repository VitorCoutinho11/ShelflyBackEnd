package com.senac.ShelflyBackEnd.enums;

public enum UsuarioLivroStatus {
     LER(1),
    LENDO(2),
    LIDO(3);

    private final Integer codigo;

    UsuarioLivroStatus(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static UsuarioLivroStatus fromCodigo(Integer codigo) {
        for (UsuarioLivroStatus status : values()) {
            if (status.getCodigo().equals(codigo)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código inválido para Status: " + codigo);
    }
}
