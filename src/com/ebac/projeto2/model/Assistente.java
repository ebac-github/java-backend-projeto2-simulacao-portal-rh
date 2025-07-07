package com.ebac.projeto2.model;

public class Assistente extends FuncionarioBatePonto implements BatePonto {

    public Assistente(String nome) {
        super(nome);
    }

    @Override
    public int getTipoFuncionario() {
        return 4;
    }
}
