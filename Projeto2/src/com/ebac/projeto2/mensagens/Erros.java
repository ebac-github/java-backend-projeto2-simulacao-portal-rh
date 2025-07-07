package com.ebac.projeto2.mensagens;

public class Erros {

    public static final String HORARIO_INVALIDO_FORMATACAO = "Atenção! O dia, horário de entrada ou saída foram inseridos de modo inválido.\nCertifique-se de você está inserindo os dados nos seguintes formatos:\nDia: dd/MM/aaaa para o dia\n hh:MM para os horários de entrada e saída.\n";
    public static final String HORARIO_ENTRADA_SAIDA_INVALIDO = "O horário de entrada não pode ser maior do que o horário de saída.\n";
    public static final String HORAS_EXTRAS_INVALIDAS = "Este funcionário só pode fazer no máximo %d horas extras de trabalho.\n";
    public static final String HORARIOS_ENTRADA_E_SAIDA_DESRESPEITADOS = "Este funcionário só pode entrar após as %s e deve sair antes das %s.\n";
    public static final String DIA_JA_MARCADO = "O dia %s já possui um registro de ponto. Para alterar, procure o seu gestor.\n";
    public static final String TIPO_FUNCIONARIO_INVALIDO = "Tipo de funcionário inválido.\n\n" + InteracaoUsuario.DIGITAR_TIPO_FUNCIONARIO;
    public static final String DIGITE_ID_PARA_REMOVER = "Digite o número do id de um funcionário para removê-lo:\n";
    public static final String DIGITE_ID_PARA_REGISTRAR_HORAS = "Digite o número do id de um funcionário para registrar suas horas:\n";
    public static final String DIGITE_ID_PARA_LISTAR_HORAS = "Digite o número do id de um funcionário para listar suas horas registradas:\n";
    public static final String ID_INVALIDO = "Entrada inválida. Digite um id válido de um funcionário para removê-lo:\n";
    public static final String ID_NAO_ENCONTRADO = "\nO id %d não existe na lista de funcionários criados. Por favor, verifique a listagem de funcionários cadastrados.\n";
    public static final String FUNCIONARIO_NAO_BATE_PONTO = "O funcionário escolhido não bate ponto.";
}
