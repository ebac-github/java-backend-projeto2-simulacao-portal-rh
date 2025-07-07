package com.ebac.projeto2.model;

import com.ebac.projeto2.business.ManipuladorEntradas;
import com.ebac.projeto2.business.ManipuladorRegistrosHoras;
import com.ebac.projeto2.excecoes.FormatoDiaEntradaSaidaInvalido;
import com.ebac.projeto2.excecoes.RegistroInvalidoException;
import com.ebac.projeto2.mensagens.InteracaoUsuario;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe absrata, feita para ser herdada por todos os funcionários que batem ponto (coordenador, analista e assistente).
 */
public abstract class FuncionarioBatePonto extends Funcionario implements BatePonto {

    private List<RegistroHoras> registrosHoras;
    private final LocalTime horarioAlmoco = LocalTime.of(1, 0);

    public FuncionarioBatePonto(String nome) {
        super(nome);
        registrosHoras = new ArrayList<>();
    }

    public void registrarHoras() {

        RegistroHoras registroHoras = null;
        boolean comandoVoltar = false;

        while (Objects.isNull(registroHoras) && !comandoVoltar)
            try {
                System.out.print(InteracaoUsuario.DIGITAR_DIA);
                String data = ManipuladorEntradas.sc.nextLine();

                System.out.printf(InteracaoUsuario.DIGITAR_HORARIO, "entrada", this.getNome());
                String entrada = ManipuladorEntradas.sc.nextLine();

                System.out.printf(InteracaoUsuario.DIGITAR_HORARIO, "saída", this.getNome());
                String saida = ManipuladorEntradas.sc.nextLine();

                registroHoras = new RegistroHoras(data, entrada, saida, this);

                ManipuladorRegistrosHoras.inserirRegistroOrdenadoPeloDia(registrosHoras, registroHoras);


            } catch (RegistroInvalidoException | FormatoDiaEntradaSaidaInvalido e) {
                System.out.println(e.getMessage());

                System.out.print(InteracaoUsuario.DIGITAR_HORAS_NOVAMENTE);
                if (!ManipuladorEntradas.sc.nextLine().equalsIgnoreCase("s"))
                    comandoVoltar = true;

            }
    }

    public List<RegistroHoras> getRegistrosHoras() {
        return registrosHoras;
    }

    @Override
    public int getQuantidadeHorasExtrasPermitidas() {
        return 3;
    }

    @Override
    public LocalTime getHorarioMinimoEntrada() {
        return LocalTime.of(6, 0);
    }

    @Override
    public LocalTime getHorarioMaximoSaida() {
        return LocalTime.of(22, 0);
    }

    public LocalTime getHorarioAlmoco() {
        return horarioAlmoco;
    }

    public abstract int getTipoFuncionario();
}

