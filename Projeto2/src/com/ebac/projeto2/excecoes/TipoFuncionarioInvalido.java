package com.ebac.projeto2.excecoes;

public class TipoFuncionarioInvalido extends RuntimeException {
    public TipoFuncionarioInvalido(String message) {
        super(message);
    }
}
