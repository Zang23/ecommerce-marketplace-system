package edu.marketplace.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import edu.marketplace.dao.ConnectionFactory;
import edu.marketplace.dao.ProdutoDAO;
import edu.marketplace.entity.Produto;

// Resumo:
// Cadastrar produto (vinculando com o id do vendedor)
// Editar produto (preço, desconto, descrição, estoque)
// Excluir produto (pelo código)
// Pesquisar produto por nome
// Obs: só vendedor pode cadastrar/editar/excluir

public class ProdutoController {

  // Essa função serve pra cadastrar um novo produto, mas só deixa se quem ta logado for vendedor.
  // O produto já sai junto com o id do vendedor que ta logado
  public String cadastrarProduto(Produto produto) {

    // Aqui verifica se é vendedor:
    if (LoginController.isVendedorLogado() == false) {
        return "Só vendedor pode cadastrar produto";
    }

    // Aqui pega o id do vendedor e coloca no produto
    Long idVendedor = LoginController.getUsuarioLogado().getId();
    produto.setIdVendedor(idVendedor);

    try {
        // Abre a conexão
        Connection conexao = ConnectionFactory.getConnection();
        ProdutoDAO dao = new ProdutoDAO(conexao);


        // Thiago Inserir denovo pls
        boolean cadastrou = dao.inserir(produto);

        conexao.close();

        // Se gravou no banco volta true
        if (cadastrou == true) {
            return "Produto cadastrado com sucesso";
        } else {
            return "Erro ao cadastrar o produto, tente de novo";
        }

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao cadastrar o produto, tente de novo";
    }
  }


  // Essa função edita um produto (mexe no preço, desconto, descrição e estoque). Só vendedor que pode mexer
  public String editarProduto(Produto produto) {

    // Mesma verificação de cima, se não for vendedor não vai
    if (LoginController.isVendedorLogado() == false) {
        return "Só vendedor pode editar produto";
    }

    try {
        Connection conexao = ConnectionFactory.getConnection();
        ProdutoDAO dao = new ProdutoDAO(conexao);

        // Thiago, aqui o DAO tem que achar o produto trocar as infos dele, passa o objeto todo, retorna o boolean pra saber se deu ou não
        boolean editou = dao.atualizar(produto);

        conexao.close();


        if (editou == true) {
            return "Produto atualizado com sucesso";
        } else {
            return "Erro ao atualizar o produto, tente de novo";
        }

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao atualizar o produto, tente de novo";
    }
  }

  // Função que apaga um produto pelo código dele. Só vendedor pode fazer isso também
  public String excluirProduto(Long codigo) {

    if (LoginController.isVendedorLogado() == false) {
        return "Só vendedor pode excluir produto";
    }

    try {
        Connection conexao = ConnectionFactory.getConnection();
        ProdutoDAO dao = new ProdutoDAO(conexao);

        // Mesmo esquema de excluir dos outros, aqui só passa o codigo do produto (Thiago)
        boolean excluiu = dao.excluir(codigo);

        conexao.close();

        if (excluiu == true) {
            return "Produto apagado com sucesso";
        } else {
            return "Erro ao apagar o produto, tente de novo";
        }

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao apagar o produto, tente de novo";
    }
  }


  // Essa função procura produtos pelo que o usuário digitou e retorna a lista dos que achou, aqui qualquer um pode usar
  public LinkedList<Produto> pesquisarPorNome(String texto) {

    // Lista vazia pra começar, se der erro ela volta vazia mesmo
    LinkedList<Produto> produtos = new LinkedList<Produto>();

    try {
        Connection conexao = ConnectionFactory.getConnection();
        ProdutoDAO dao = new ProdutoDAO(conexao);

        // Thiago aqui vai precisar de uma função que pega o texto e procura no banco de dados
        produtos = dao.pesquisarPorNome(texto);

        conexao.close();

    } catch (SQLException erro) {
        erro.printStackTrace();
    }

    return produtos;
  }
}