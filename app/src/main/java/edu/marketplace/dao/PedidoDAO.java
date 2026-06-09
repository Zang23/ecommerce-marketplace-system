package edu.marketplace.dao;

import edu.marketplace.entity.Pedido;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;
public class PedidoDAO implements CrudDAO<Pedido, Long>{

  private Connection con;

  public PedidoDAO(Connection con){
    this.con = con;
  }

  @Override
  public boolean inserir (Pedido p){
    return false;
  }

  @Override
  public boolean atualizar(Pedido p){
    return false;
  }

  @Override
  public boolean excluir(Long id){
    return false;
  }

  @Override
  public Pedido buscarPorId(Long id){
    return null;
  }

  public Long criarPedido(Long idComprador){

    String sql = """
      INSERT INTO pedido
      (comprador_id, valor_total, data_finalizacao, status)
      VALUES (?, 0, ?, ?)
    """;

    try(PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

      stm.setLong(1, idComprador);
      stm.setDate(2, Date.valueOf(LocalDate.now()));
      stm.setString(3, "ABERTO");

      stm.executeUpdate();

      ResultSet rs = stm.getGeneratedKeys();

      if(rs.next()){
        return rs.getLong(1);
      }

    }catch(Exception e){

      System.out.println("Erro ao criar pedido: " + e.getMessage());
    }

    return null;  


  }

  public boolean finalizar(Long idPedido, double total){

    String sql = """
      UPDATE pedido
      SET valor_total = ?,
          data_finalizacao = ?,
          status = ?
      WHERE id = ?
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){
      
      stm.setDouble(1, total);
      stm.setDate(2, Date.valueOf(LocalDate.now()));
      stm.setString(3, "FINALIZADO");
      stm.setLong(4, idPedido);

      stm.executeUpdate();

      return true;

    }catch(Exception e){
      System.out.println("Erro ao finalizar pedido: " + e.getMessage());
      return false;
    }



  }

  public LinkedList<Pedido> listarHistoricoComprador(Long idComprador){

    LinkedList<Pedido> lista = new LinkedList<>();

    String sql = """
      SELECT * 
      FROM pedido
      WHERE id_comprador = ?
      AND   status = ?
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){

      stm.setLong(1, idComprador);
      stm.setString(2, "FINALIZADO");


      ResultSet rs = stm.executeQuery();

      while(rs.next()){
          
        Pedido p = new Pedido();

        p.setStatus(rs.getString("status"));
        p.setValorTotal(rs.getDouble("valor_total"));
        p.setIdComprador(rs.getLong("id_comprador"));
        p.setDataFinalizacao(rs.getDate("data_finalizacao").toLocalDate());
        p.setId(rs.getLong("id"));

        lista.add(p);

      }

      return lista;

    }catch(Exception e){
      System.out.println("Erro ao listar historico do comprador: " + e.getMessage());
    }

    return lista;
  }


  public LinkedList<Pedido> listarVendasVendedor(Long idVendedor){

    LinkedList<Pedido> lista = new LinkedList<>();

    String sql = """
      SELECT DISTINCT p.*
      FROM pedido p, produto_carrinho pc, produto pr
      WHERE p.id = pc.id_pedido 
      AND   pr.id = pc.id_produto
      AND   pr.id_vendedor = ?
      AND   p.status = ?
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){

      stm.setLong(1, idVendedor);
      stm.setString(2, "FINALIZADO");

      ResultSet rs = stm.executeQuery();

      while(rs.next()){
       
        Pedido p = new Pedido();

        p.setId(rs.getLong("id"));
        p.setIdComprador(rs.getLong("id_comprador"));
        p.setValorTotal(rs.getDouble("valor_total"));
        p.setStatus(rs.getString("status"));
        p.setDataFinalizacao(rs.getDate("data_finalizacao").toLocalDate());

        lista.add(p);

      }
      return lista;

    }catch(Exception e){
      System.out.println("Erro ao listar vendas do vendedor: " + e.getMessage());
    }

    return lista;

  }


  

}
