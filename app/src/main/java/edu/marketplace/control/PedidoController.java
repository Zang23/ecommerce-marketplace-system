package edu.marketplace.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import edu.marketplace.dao.ConnectionFactory;
import edu.marketplace.dao.PedidoDAO;
import edu.marketplace.entity.Pedido;

// Resumo:
// Criar o pedido (abre o carrinho, em aberto)
// Finalizar pedido (pega o carrinho, calcula o total e fecha)
// Listar histórico de compras do comprador
// Listar vendas do vendedor
// Obs: o carrinho é um pedido "ABERTO", quando finaliza ele vira "FINALIZADO"

public class PedidoController {

  // Essa função abre um pedido novo "em aberto" pro comprador (é o carrinho dele).
  // Devolve o id que o banco gerou, porque o carrinho vai precisar dele pra pendurar os itens
  public Long criarPedido(Long idComprador) {

    // Se der erro volta null, ai a tela sabe que não rolou
    Long idPedido = null;


    try {
        Connection conexao = ConnectionFactory.getConnection();
        PedidoDAO dao = new PedidoDAO(conexao);

        // Thiago, essa cria o pedido em aberto e tem que DEVOLVER o id que o banco gerou (não é boolean igual os outros).
        // Detalhe chato: a data_finalizacao é NOT NULL, então coloca a data de hoje provisória e depois o finalizar troca
        idPedido = dao.criarPedido(idComprador);

        conexao.close();

    } catch (SQLException erro) {
        erro.printStackTrace();
    }

    return idPedido;
  }

  // Essa função fecha o pedido: pega o total do carrinho, salva ele e muda o status pra finalizado
  public String finalizarPedido(Long idPedido) {

    // Reuso o CarrinhoController pra somar o total, assim não preciso refazer a conta aqui
    CarrinhoController carrinho = new CarrinhoController();

    double total = carrinho.calcularTotal(idPedido);

    try {
        Connection conexao = ConnectionFactory.getConnection();
        PedidoDAO dao = new PedidoDAO(conexao);

        // Thiago, aqui o DAO grava o total, coloca a data de hoje na finalização e troca o status pra FINALIZADO
        boolean finalizou = dao.finalizar(idPedido, total);

        conexao.close();


        if (finalizou == true) {
            return "Pedido finalizado com sucesso";
        } else {
            return "Erro ao finalizar o pedido, tente de novo";
        }

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao finalizar o pedido, tente de novo";
    }
  }


  // Essa função traz as compras que o comprador já finalizou (o histórico dele)
  public LinkedList<Pedido> listarHistoricoComprador(Long idComprador) {

    // Lista vazia pra começar, se der ruim volta vazia
    LinkedList<Pedido> pedidos = new LinkedList<Pedido>();

    try {
        Connection conexao = ConnectionFactory.getConnection();
        PedidoDAO dao = new PedidoDAO(conexao);

        // Thiago aqui pega os pedidos daquele comprador que já tão FINALIZADOS (o carrinho aberto não entra)
        pedidos = dao.listarHistoricoComprador(idComprador);

        conexao.close();

    } catch (SQLException erro) {
        erro.printStackTrace();
    }

    return pedidos;
  }

  // Essa função traz as vendas de um vendedor (os pedidos finalizados que tem algum produto dele)
  public LinkedList<Pedido> listarVendasVendedor(Long idVendedor) {

    LinkedList<Pedido> vendas = new LinkedList<Pedido>();

    try {
        Connection conexao = ConnectionFactory.getConnection();
        PedidoDAO dao = new PedidoDAO(conexao);

        // Thiago essa é parecida com a do carrinho na parte chata: tem que juntar pedido + produto_carrinho + produto
        // pra achar os pedidos finalizados que tem produto desse vendedor (usa DISTINCT pra não repetir o mesmo pedido)
        vendas = dao.listarVendasVendedor(idVendedor);

        conexao.close();

    } catch (SQLException erro) {
        erro.printStackTrace();
    }

    return vendas;
  }
}