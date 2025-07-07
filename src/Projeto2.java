import com.ebac.projeto2.business.ManipuladorEntradas;
import com.ebac.projeto2.business.ServicosFuncionario;
import com.ebac.projeto2.mensagens.InteracaoUsuario;

public class Projeto2 {

    public static void main(String[] args) throws Exception {

        System.out.println(InteracaoUsuario.BOAS_VINDAS);
        while (true) {
            System.out.println(InteracaoUsuario.COMANDOS_INICIAIS);

            try {
                int comando = ManipuladorEntradas.sc.nextInt();
                ManipuladorEntradas.sc.nextLine();

                if (comando < 1 || comando > 5)
                    break;

                if (comando == 1)
                    ServicosFuncionario.criarFuncionario();
                else if (comando == 2)
                    ServicosFuncionario.listarFuncionarios();
                else if (comando == 3)
                    ServicosFuncionario.removerFuncionario();
                else if (comando == 4)
                    ServicosFuncionario.registrarHoras();
                else
                    ServicosFuncionario.listarHorasRegistradas();

            } catch (Exception e) {
                break;
            }
        }
        ManipuladorEntradas.sc.close();
        System.out.println(InteracaoUsuario.DESPEDIDA);
    }
}































