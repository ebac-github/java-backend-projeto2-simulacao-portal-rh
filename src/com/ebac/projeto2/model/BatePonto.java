package com.ebac.projeto2.model;

import java.time.LocalTime;

/**
 * Interface criada com métodos para todos os tipos de funcionários que batem ponto.
 */
public interface BatePonto {
    public void registrarHoras();

    public int getQuantidadeHorasExtrasPermitidas();

    public LocalTime getHorarioMinimoEntrada();

    public LocalTime getHorarioMaximoSaida();
}
