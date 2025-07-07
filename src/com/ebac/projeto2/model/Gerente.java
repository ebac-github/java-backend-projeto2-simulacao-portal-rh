package com.ebac.projeto2.model;

public class Gerente extends Funcionario {

    public Gerente(String nome) {
        super(nome);
    }

    @Override
    public int getTipoFuncionario() {
        return 1;
    }
}
