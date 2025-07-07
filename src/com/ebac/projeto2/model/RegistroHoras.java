package com.ebac.projeto2.model;

import com.ebac.projeto2.excecoes.FormatoDiaEntradaSaidaInvalido;
import com.ebac.projeto2.excecoes.RegistroInvalidoException;
import com.ebac.projeto2.mensagens.Erros;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RegistroHoras {

    private LocalDate dia;
    private LocalTime entrada;
    private LocalTime saida;
    private LocalTime horasDesequilibradas;
    private FuncionarioBatePonto funcionarioBatePonto;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RegistroHoras(String dia, String entrada, String saida, FuncionarioBatePonto funcionarioBatePonto) {

        // Validando o tamanho da string dos horários de entrada e saída.
        if (entrada.length() != 5 || saida.length() != 5)
            throw new FormatoDiaEntradaSaidaInvalido(Erros.HORARIO_INVALIDO_FORMATACAO);

        try {
            // Realizando conversão das entradas String para LocalDate e LocalTime, conforme necessidade.
            this.dia = LocalDate.parse(dia, DATE_TIME_FORMATTER);
            this.entrada = LocalTime.parse(entrada);
            this.saida = LocalTime.parse(saida);
            this.funcionarioBatePonto = funcionarioBatePonto;

            validarRegistro();
        } catch (FormatoDiaEntradaSaidaInvalido e) {
            System.out.println(e.getMessage());
            throw new FormatoDiaEntradaSaidaInvalido(Erros.HORARIO_INVALIDO_FORMATACAO);
        }
    }

    private void validarRegistro() {

        //O horário de saída não pode ser maior que o horário de entrada.
        if (entrada.isAfter(saida) || entrada.equals(saida))
            throw new RegistroInvalidoException(Erros.HORARIO_ENTRADA_SAIDA_INVALIDO);

        this.horasDesequilibradas = calcularHorasExtrasOuHorasADever();

        //Cada tipo de funcionário tem uma quantidade de horas extras permitidas. Validação do respeito ao limite de horas extras de cada tipo de funcionário.
        if (horasDesequilibradas.getHour() > this.funcionarioBatePonto.getQuantidadeHorasExtrasPermitidas()
                || horasDesequilibradas.getHour() == this.funcionarioBatePonto.getQuantidadeHorasExtrasPermitidas() && horasDesequilibradas.getMinute() > 0)
            throw new RegistroInvalidoException(String.format(Erros.HORAS_EXTRAS_INVALIDAS, this.funcionarioBatePonto.getQuantidadeHorasExtrasPermitidas()));

        //Validando se os horários mínimos e máximos de saída são respeitados (06:00 e 22:00)
        if (this.entrada.isBefore(funcionarioBatePonto.getHorarioMinimoEntrada()) || this.saida.isAfter(funcionarioBatePonto.getHorarioMaximoSaida()))
            throw new RegistroInvalidoException(String.format(Erros.HORARIOS_ENTRADA_E_SAIDA_DESRESPEITADOS,
                    this.funcionarioBatePonto.getHorarioMinimoEntrada().toString(),
                    this.funcionarioBatePonto.getHorarioMaximoSaida().toString()));

    }

    private LocalTime calcularHorasExtrasOuHorasADever() {

        Duration calculoHorasExtrasOuADever = Duration.between(this.entrada, this.saida)
                .minusHours(this.funcionarioBatePonto.getHorarioAlmoco().getHour());

        //horas extras = (total de horas trabalhadas - 8 horas) para casos de horário mínimo de 8 horas cumprido.
        //horas a dever = (8 horas - horas trabalhadas ) para casos que as 8 horas de trabalho não foram atingidas no dia.
        return calculoHorasExtrasOuADever.toHours() >= 8 ?
                LocalTime.of((int) calculoHorasExtrasOuADever.toHours(), (int) calculoHorasExtrasOuADever.toMinutesPart()).minusHours(8)
                : LocalTime.of(8, 0).minusHours(calculoHorasExtrasOuADever.toHours()).minusMinutes(calculoHorasExtrasOuADever.toMinutesPart());
    }

    public LocalDate getDataMarcacao() {
        return dia;
    }

    @Override
    public String toString() {
        return "Funcionário: " + this.funcionarioBatePonto.getNome() + " | data:" + this.getDataMarcacao() + " | Entrada:" + this.entrada + " | Saída: " + this.saida;
    }
}
