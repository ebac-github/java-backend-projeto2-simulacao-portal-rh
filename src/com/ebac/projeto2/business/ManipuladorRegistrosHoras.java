package com.ebac.projeto2.business;

import com.ebac.projeto2.excecoes.RegistroInvalidoException;
import com.ebac.projeto2.mensagens.Erros;
import com.ebac.projeto2.model.RegistroHoras;

import java.util.List;
/**
 * Classe para inserir o novo registro de horas na lista de registros do usuário
 * de modo ordenado, de acordo com o dia.
 */
public class ManipuladorRegistrosHoras {

    public static void inserirRegistroOrdenadoPeloDia(List<RegistroHoras> registrosHoras, RegistroHoras registroHoras) {

        if (registrosHoras.isEmpty()) {
            registrosHoras.add(registroHoras);
            return;
        }

        if (registrosHoras.getFirst().getDataMarcacao().equals(registroHoras.getDataMarcacao()))
            throw new RegistroInvalidoException(String.format(Erros.DIA_JA_MARCADO, registroHoras.getDataMarcacao().format(RegistroHoras.DATE_TIME_FORMATTER)));

        for (int i = 0; i < registrosHoras.size(); i++) {
            if (registrosHoras.get(i).getDataMarcacao().equals(registroHoras.getDataMarcacao()))
                throw new RegistroInvalidoException(String.format(Erros.DIA_JA_MARCADO, registroHoras.getDataMarcacao().format(RegistroHoras.DATE_TIME_FORMATTER)));

            if (registrosHoras.get(i).getDataMarcacao().isAfter(registroHoras.getDataMarcacao())) {
                registrosHoras.add(i, registroHoras);
                break;
            }

            if (i == registrosHoras.size() - 1) {
                registrosHoras.add(registroHoras);
                break;
            }
        }
    }
}
