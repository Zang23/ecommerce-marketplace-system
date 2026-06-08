package edu.marketplace.control;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import edu.marketplace.dao.CartaoDAO;
import edu.marketplace.dao.ConnectionFactory;
import edu.marketplace.entity.UsuarioCartao;

// Resumo:
// Cadastrar cartão (número, validade, cvv, nome do titular, vinculado ao idUsuario)
// Listar os cartões do usuário
// Editar cartão
// Excluir cartão (pelo número dele+)

public class CartaoController {

  // Essa função cria um cartão novo pro usuário. Devolve uma mensagem dizendo se deu certo
  public String cadastrarCartao(UsuarioCartao cartao) {

    try {
        // Aqui cria a conexão e ai vai pro dAO
        Connection conexao = ConnectionFactory.getConnection();
        CartaoDAO dao = new CartaoDAO(conexao);

        // Aqui a mesma função inserir que é utilizada nos outros, só que no cartão (Thiago - Deixar o nome porque vai que tu ta pesquisando com Ctrl + f)
        boolean cadastrou = dao.inserir(cartao);

        conexao.close();

        // O inserir devolve true se deu certo
        if (cadastrou == true) {
            return "Cartão cadastrado com sucesso";
        } else {
            return "Erro ao cadastrar o cartão, tente de novo";
        }

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao cadastrar o cartão, tente de novo";
    }
  }

  // Essa função devolve uma lista dos cartões do comprador. Se der erro, devolve a lista >>>VAZIA<<<, posso mudar se precisar
  public LinkedList<UsuarioCartao> listarCartoes(Long idComprador) {
    
    LinkedList<UsuarioCartao> cartoes = new LinkedList<UsuarioCartao>();

    try {
        Connection conexao = ConnectionFactory.getConnection();
        CartaoDAO dao = new CartaoDAO(conexao);

        // Thiago aqui vai precisar de uma função que usa o id do comprador pra achar os cartões que ele tem e devolver a lista deles (devolver ela vazia se não tiver nenhum ou der erro)
        cartoes = dao.listarPorComprador(idComprador);

        conexao.close();

    } catch (SQLException erro) {
        erro.printStackTrace();
    }

    return cartoes;
  }

  // Essa função edita o cartão
  public String editarCartao(UsuarioCartao cartao) {

    try {
        Connection conexao = ConnectionFactory.getConnection();
        CartaoDAO dao = new CartaoDAO(conexao);

        // Thiago, aqui o DAO vai ter que alterar o cartão, passa o objeto mesmo
        boolean editou = dao.atualizar(cartao);

        conexao.close();

        if (editou == true) {
            return "Cartão atualizado com sucesso";
        } else {
            return "Erro ao atualizar o cartão, tente de novo";
        }

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao atualizar o cartão, tente de novo";
    }
  }

  // Essa função apaga um cartão pelo número dele e ja devolve uma mensagem
  public String excluirCartao(String numeroCartao) {

    try {
        Connection conexao = ConnectionFactory.getConnection();
        CartaoDAO dao = new CartaoDAO(conexao);

        // Mesmo nome de função do outro pra excluir, aqui passa o numero do cartão
        boolean excluiu = dao.excluir(numeroCartao);

        conexao.close();

        if (excluiu == true) {
            return "Cartão apagado com sucesso";
        } else {
            return "Erro ao apagar o cartão, tente de novo";
        }

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao apagar o cartão, tente de novo";
    }
  }
}