package com.ebac.projeto2.model;

public class Estagiario extends Funcionario {

    public Estagiario(String nome) {
        super(nome);
    }

    @Override
    public int getTipoFuncionario() {
        return 5;
    }
}
