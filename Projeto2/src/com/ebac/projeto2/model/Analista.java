package com.ebac.projeto2.model;

public class Analista extends FuncionarioBatePonto implements BatePonto {

    public Analista(String nome) {
        super(nome);
    }

    @Override
    public int getTipoFuncionario() {
        return 3;
    }
}
