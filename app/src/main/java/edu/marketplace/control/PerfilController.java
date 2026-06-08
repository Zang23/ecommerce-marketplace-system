package edu.marketplace.control;
import java.sql.Connection;
import java.sql.SQLException;
import edu.marketplace.dao.CompradorDAO;
import edu.marketplace.dao.ConnectionFactory;
import edu.marketplace.entity.Comprador;

// Resumo
// Editar os dados do usuário (nome, telefone, endereço, etc)
// Excluir a conta (recebe o idUsuario e apaga)

public class PerfilController {
  // Essa função atualiza os dados do usuário (nome, telefone, endereço) chamando o DAO.
  // Devolve uma mensagem dizendo se deu certo ou não
  public String editarPerfil(Comprador comprador) {

    try {
        // Abre a conexão e passa pro DAO
        Connection conexao = ConnectionFactory.getConnection();
        CompradorDAO dao = new CompradorDAO(conexao);

        // E Thiago aqui vai faltar uma função no DAO pra atualizar os dados do comprador 
        boolean atualizou = dao.atualizar(comprador);
        conexao.close();

        // O atualizar devolve true se mudou
        if (atualizou == true) {
            return "Dados atualizados com sucesso";
        } else {
            return "Erro ao atualizar, tente de novo";
        }

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao atualizar, tente de novo";
    }
  }

  // Função que apaga a conta do usuário pelo id. Aqui tem a mesma lógica do Login de que o vendedor é um comprador, então se apagar o comprador
  // já apaga o resto junto (De acordo com oq eu pesquisei, posso estar errado), então acredito que essa função serve pros dois
  public String excluirConta(Long idUsuario) {

    try {
        Connection conexao = ConnectionFactory.getConnection();
        CompradorDAO dao = new CompradorDAO(conexao);

        // Thiago, aqui vai precisar de uma função no DAO pra apagar o comprador pelo id (devolve true se apagou)
        boolean excluiu = dao.excluir(idUsuario);

        conexao.close();
        if (excluiu == true) {
            return "Conta apagada com sucesso";
        } else {
            return "Erro ao apagar a conta, tente de novo";
        }
    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao apagar a conta, tente de novo";
    }
  }
}