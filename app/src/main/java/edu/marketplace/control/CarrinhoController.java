package edu.marketplace.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import edu.marketplace.dao.ConnectionFactory;
import edu.marketplace.dao.ProdutoCarrinhoDAO;
import edu.marketplace.entity.ProdutoCarrinho;

// Resumo:
// Adicionar item (idPedido, codProduto, quantidade)
// Remover item (idPedido, codProduto)
// Atualizar quantidade
// Listar os itens do carrinho
// Calcular o total
// Obs: o carrinho fica ligado a um pedido (idPedido)

public class CarrinhoController {

  // Função que adiciona um produto no carrinho, ele retorna uma mensagem dizendo no que deu
  public String adicionarItem(Long idPedido, Long codProduto, int quantidade) {

    // Cria o item
    ProdutoCarrinho item = new ProdutoCarrinho(codProduto, idPedido, quantidade);


    try {
        Connection conexao = ConnectionFactory.getConnection();
        ProdutoCarrinhoDAO dao = new ProdutoCarrinhoDAO(conexao);

        // Precisa da função de inserir no DAO
        boolean adicionou = dao.inserir(item);

        conexao.close();

        if (adicionou == true) {
            return "Produto adicionado ao carrinho";
        } else {
            return "Erro ao adicionar no carrinho, tente de novo";
        }

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao adicionar no carrinho, tente de novo";
    }
  }

  // Função pra remover
  public String removerItem(Long idPedido, Long codProduto) {

    try {
        Connection conexao = ConnectionFactory.getConnection();
        ProdutoCarrinhoDAO dao = new ProdutoCarrinhoDAO(conexao);


        // Thiago aqui é pra apagar
        boolean removeu = dao.remover(idPedido, codProduto);

        conexao.close();

        if (removeu == true) {
            return "Produto removido do carrinho";
        } else {
            return "Erro ao remover do carrinho, tente de novo";
        }

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao remover do carrinho, tente de novo";
    }
  }


  // Função pra atualizar a quantidade de um item que já está no carrinho
  public String atualizarQuantidade(Long idPedido, Long codProduto, int novaQtd) {

    try {
        Connection conexao = ConnectionFactory.getConnection();
        ProdutoCarrinhoDAO dao = new ProdutoCarrinhoDAO(conexao);

        // Thiago aqui é só trocar o numero da quantidade daquele produto no carrinho pela nova
        boolean atualizou = dao.atualizarQuantidade(idPedido, codProduto, novaQtd);

        conexao.close();

        if (atualizou == true) {
            return "Quantidade atualizada";
        } else {
            return "Erro ao atualizar a quantidade, tente de novo";
        }

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao atualizar a quantidade, tente de novo";
    }
  }

  // Função que devolve a lista de itens do carrinho
  public LinkedList<ProdutoCarrinho> listarItens(Long idPedido) {

    // Lista vazia pra começar, se der erro / tiver nada ela volta vazia
    LinkedList<ProdutoCarrinho> itens = new LinkedList<ProdutoCarrinho>();

    try {
        Connection conexao = ConnectionFactory.getConnection();
        ProdutoCarrinhoDAO dao = new ProdutoCarrinhoDAO(conexao);

        // Aqui pega os itens do carrinho usando o id do pedido Thiago
        itens = dao.listarPorPedido(idPedido);

        conexao.close();

    } catch (SQLException erro) {
        erro.printStackTrace();
    }


    return itens;
  }

  // Função pra ver o preço total dos itens do carrinho
  public double calcularTotal(Long idPedido) {

    // Começa zerado
    double total = 0;

    try {
        Connection conexao = ConnectionFactory.getConnection();
        ProdutoCarrinhoDAO dao = new ProdutoCarrinhoDAO(conexao);

        // Aqui tem que devolver o valor total do carrinho, igual a gente falou, Thiago
        total = dao.calcularTotal(idPedido);

        conexao.close();

    } catch (SQLException erro) {
        erro.printStackTrace();
    }

    return total;
  }
}