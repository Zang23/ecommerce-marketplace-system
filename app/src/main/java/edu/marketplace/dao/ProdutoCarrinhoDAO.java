package edu.marketplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import edu.marketplace.entity.ProdutoCarrinho;
public class ProdutoCarrinhoDAO implements CrudDAO<ProdutoCarrinho, Long>{


  private Connection con;
  public ProdutoCarrinhoDAO(Connection con){
    this.con = con;
  }

  @Override
  public boolean inserir (ProdutoCarrinho p){
  
    String sql = """
      INSERT INTO produto_carrinho
        (cod_produto, id_pedido, qtd_carrinho)
      VALUES
        (?,?,?)
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){
  
      stm.setLong(1, p.getCodProduto());
      stm.setLong(2, p.getIdPedido());
      stm.setInt(3, p.getQtdCarrinho());

      stm.executeUpdate();

      return true;
    }catch(Exception e){
      System.out.println("Erro ao Inserir novo produto: " + e.getMessage());
      return false;
    }

    
  }

  @Override
  public boolean atualizar(ProdutoCarrinho p){
    return false;
  }

  @Override
  public boolean excluir(Long id){
    return false;
  }

  public boolean remover(Long idPedido, Long codProduto){
    
    String sql = """
      DELETE FROM produto_carrinho
      WHERE cod_produto = ?
      AND   id_pedido = ?
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){
      
      stm.setLong(1, codProduto);
      stm.setLong(2, idPedido);

      stm.executeUpdate();

      return true;

    }catch(Exception e){
      System.out.println("Erro ao deletar produto do pedido: " + e.getMessage());
      return false;
    }

  }

  @Override
  public ProdutoCarrinho buscarPorId(Long id){
    return null;
  }

  public boolean atualizarQuantidade(Long idPedido, Long codProduto, int novaQtd){

    String sql = """
      UPDATE produto_carrinho
      SET qtd_carrinho = ?
      WHERE cod_produto = ?
      AND   id_pedido = ?
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){

      stm.setInt(1, novaQtd);
      stm.setLong(2, codProduto);
      stm.setLong(3, idPedido);

      stm.executeUpdate();

      return true;

    }catch(Exception e){
      System.out.println("Erro ao atualizar quantidade do carrinho: " + e.getMessage());
      return false;
    }

  }

  public LinkedList<ProdutoCarrinho> listarPorPedido(Long idPedido){
    
    LinkedList<ProdutoCarrinho> lista = new LinkedList<>();

    String sql = """
      SELECT * 
      FROM produto_carrinho
      WHERE id_pedido = ?
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){
      
      stm.setLong(1, idPedido);

      ResultSet rs = stm.executeQuery();

      while(rs.next()){
        
        ProdutoCarrinho p = new ProdutoCarrinho();

        p.setIdPedido(rs.getLong("id_pedido"));
        p.setCodProduto(rs.getLong("cod_produto"));
        p.setQtdCarrinho(rs.getInt("qtd_carrinho"));

        lista.add(p);
      }

      return lista;

    }catch(Exception e){
      System.out.println("Erro ao listar produtos de um pedido: " + e.getMessage());
      return lista;
    }

  }

  public double calcularTotal(Long idPedido){
    
    String sql = """
      SELECT SUM(p.valor_unitario *  pc.qtd_carrinho) AS total
      FROM produto_carrinho pc, produto p
      WHERE pc.cod_produto = p.codigo
      AND   pc.id_pedido = ?
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){
    
      stm.setLong(1, idPedido);

      ResultSet rs = stm.executeQuery();

      if(rs.next()){
        double total = rs.getDouble("total");
        return total;
      }


    }catch(Exception e){
      System.out.println("Erro ao calcular o valor total am");
    }

    return 0.0;
  }
}
