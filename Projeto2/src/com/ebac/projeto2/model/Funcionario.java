package com.ebac.projeto2.model;

import com.ebac.projeto2.enums.TipoFuncionario;
import com.ebac.projeto2.business.ServicosFuncionario;

/**
 * Classe mais genérica do modelo de negócio, abstrata, visto que nenhum funcionário tem o cargo "funcionário", não faz sentido instanciar.
 */
public abstract class Funcionario {

    private int idFuncionario;
    private final String nome;

    public Funcionario(String nome) {
        this.nome = nome;
        idFuncionario = ServicosFuncionario.todosFuncionarios.size();
    }

    public String getNome() {
        return nome;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public abstract int getTipoFuncionario();

    @Override
    public String toString() {
        return String.format("Nome: %s | id: %d | Tipo: %s", this.nome, this.idFuncionario, TipoFuncionario.fromTipoFuncionario(getTipoFuncionario()).name());
    }
}
