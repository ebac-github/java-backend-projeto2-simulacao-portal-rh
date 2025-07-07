package com.ebac.projeto2.model;

import java.time.LocalTime;

public class Coordenador extends FuncionarioBatePonto implements BatePonto {

    public Coordenador(String nome) {
        super(nome);
    }

    @Override
    public int getQuantidadeHorasExtrasPermitidas() {
        return 5;
    }

    @Override
    public LocalTime getHorarioMaximoSaida() {
        return LocalTime.of(23, 0);
    }

    @Override
    public int getTipoFuncionario() {
        return 2;
    }
}
