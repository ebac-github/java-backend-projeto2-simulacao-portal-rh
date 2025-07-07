package com.ebac.projeto2.business;

import com.ebac.projeto2.TIPO_FUNCIONARIO;
import com.ebac.projeto2.excecoes.TipoFuncionarioInvalido;
import com.ebac.projeto2.mensagens.Erros;
import com.ebac.projeto2.mensagens.InteracaoUsuario;
import com.ebac.projeto2.model.*;

import java.util.ArrayList;
import java.util.List;

public class ServicosFuncionario {

    public static List<Funcionario> todosFuncionarios = new ArrayList<>();

    /**
     * Cria o tipo do funcionário de acordo com os parâmetros passados e o adiciona na lista de funcionários.
     */
    public static void criarFuncionario() {

        System.out.println(InteracaoUsuario.DIGITE_NOME_FUNCIONARIO);
        String nome = ManipuladorEntradas.sc.nextLine();

        try {
            System.out.println(InteracaoUsuario.DIGITAR_TIPO_FUNCIONARIO);
            int tipoFuncionarioCodigo = ManipuladorEntradas.sc.nextInt();
            ManipuladorEntradas.sc.nextLine();
            TIPO_FUNCIONARIO tipoFuncionario = TIPO_FUNCIONARIO.fromTipoFuncionario(tipoFuncionarioCodigo);

            if (tipoFuncionario.equals(TIPO_FUNCIONARIO.GERENTE))
                todosFuncionarios.add(new Gerente(nome));
            else if (tipoFuncionario.equals(TIPO_FUNCIONARIO.COORDENADOR))
                todosFuncionarios.add(new Coordenador(nome));
            else if (tipoFuncionario.equals(TIPO_FUNCIONARIO.ANALISTA))
                todosFuncionarios.add(new Analista(nome));
            else if (tipoFuncionario.equals(TIPO_FUNCIONARIO.ASSISTENTE))
                todosFuncionarios.add(new Assistente(nome));
            else if (tipoFuncionario.equals(TIPO_FUNCIONARIO.ESTAGIARIO))
                todosFuncionarios.add(new Estagiario(nome));

        } catch (TipoFuncionarioInvalido e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lista todos os funcionários cadastrados.
     */
    public static void listarFuncionarios() {
        if (todosFuncionarios.isEmpty()) {
            System.out.println(InteracaoUsuario.LISTAGEM_VAZIA);
            return;
        }

        System.out.println(InteracaoUsuario.LISTAGEM_DE_FUNCIONARIOS);
        for (Funcionario todosFuncionario : todosFuncionarios)
            System.out.println(todosFuncionario);

        System.out.print("\n\n");
    }

    /**
     * Remove um funcionário da lista.
     */
    public static void removerFuncionario() {

        try {
            System.out.println(Erros.DIGITE_ID_PARA_REMOVER);
            int idARemover = ManipuladorEntradas.sc.nextInt();
            ManipuladorEntradas.sc.nextLine();

            for (int i = 0; i < todosFuncionarios.size(); i++) {
                if (todosFuncionarios.get(i).getIdFuncionario() == idARemover) {
                    todosFuncionarios.remove(i);
                    return;
                }
            }
            System.out.printf(Erros.ID_NAO_ENCONTRADO, idARemover);

        } catch (Exception e) {
            System.out.println(Erros.ID_INVALIDO);
        }
    }

    /**
     * Registra as horas de entrada e saída em um dia para um funcionário.
     */
    public static void registrarHoras() {
        System.out.println(Erros.DIGITE_ID_PARA_REGISTRAR_HORAS);
        int idParaRegistrarHoras = ManipuladorEntradas.sc.nextInt();
        ManipuladorEntradas.sc.nextLine();

        for (int i = 0; i < todosFuncionarios.size(); i++) {
            if (todosFuncionarios.get(i).getIdFuncionario() == idParaRegistrarHoras) {
                try {
                    FuncionarioBatePonto funcionarioParaRegistrarHoras = (FuncionarioBatePonto) todosFuncionarios.get(i);
                    funcionarioParaRegistrarHoras.registrarHoras();
                    return;
                } catch (Exception e) {
                    System.out.println(Erros.FUNCIONARIO_NAO_BATE_PONTO);
                    return;
                }
            }
        }
        System.out.printf(Erros.ID_NAO_ENCONTRADO, idParaRegistrarHoras);
    }

    /**
     * Lista todas as horas de dias trabalhados para um funcionário buscado pelo id.
     */
    public static void listarHorasRegistradas() {
        System.out.println(Erros.DIGITE_ID_PARA_LISTAR_HORAS);
        int idParaListarHoras = ManipuladorEntradas.sc.nextInt();
        ManipuladorEntradas.sc.nextLine();

        for (int i = 0; i < todosFuncionarios.size(); i++) {
            if (todosFuncionarios.get(i).getIdFuncionario() == idParaListarHoras) {
                try {
                    // Realizando downcasting para um funcionário que bate ponto.
                    FuncionarioBatePonto funcionarioParaRegistrarHoras = (FuncionarioBatePonto) todosFuncionarios.get(i);

                    for (RegistroHoras registro : funcionarioParaRegistrarHoras.getRegistrosHoras())
                        System.out.println(registro);

                    return;
                } catch (Exception e) {
                    // Em caso de erro no casting acima, ele é pego aqui e exibe para o usuário a mensagem de que o funcionário escolhido não bate ponto (estagiário ou gerente).
                    System.out.println(Erros.FUNCIONARIO_NAO_BATE_PONTO);
                    return;
                }
            }
        }
        System.out.printf(Erros.ID_NAO_ENCONTRADO, idParaListarHoras);
    }
}
