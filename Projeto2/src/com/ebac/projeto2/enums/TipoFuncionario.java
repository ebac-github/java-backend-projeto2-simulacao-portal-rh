package com.ebac.projeto2.enums;

import com.ebac.projeto2.excecoes.TipoFuncionarioInvalido;
import com.ebac.projeto2.mensagens.Erros;

public enum TipoFuncionario {

    GERENTE(1), COORDENADOR(2), ANALISTA(3), ASSISTENTE(4), ESTAGIARIO(5);

    private int tipoFuncionario;

    TipoFuncionario(int tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public int getTipoFuncionario() {
        return tipoFuncionario;
    }

    /**
     * Permite a busca de um tipo de funcionário pelo tipo passado por parâmetro, retornando o enum que corresponde ao código do tipo.
     * @param tipoFuncionario
     * @return
     */
    public static TipoFuncionario fromTipoFuncionario(int tipoFuncionario) {
        for (TipoFuncionario dia : values()) {
            if (dia.getTipoFuncionario() == (tipoFuncionario)) {
                return dia;
            }
        }
        throw new TipoFuncionarioInvalido(Erros.TIPO_FUNCIONARIO_INVALIDO);
    }
}
