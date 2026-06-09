package edu.marketplace.dao;

import edu.marketplace.entity.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;


public class ProdutoDAO implements CrudDAO<Produto, Long>{

  private Connection con;

  public ProdutoDAO (Connection con){
    this.con = con;
  }

  @Override
  public boolean inserir(Produto p){
    
    String sql = """
      INSERT INTO 
      produto (id_vendedor, valor_unitario, categoria, qtd_estoque, desconto, descricao)
      VALUES(?,?,?,?,?,?)
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){
      
      stm.setLong(1, p.getIdVendedor());
      stm.setDouble(2, p.getValorUnitario());
      stm.setString(3, p.getCategoria());
      stm.setInt(4, p.getQtdEstoque());
      stm.setDouble(5, p.getDesconto());
      stm.setString(6, p.getDescricao());

      stm.executeUpdate();

      return true;

    }catch(Exception e){
      System.out.println("Erro ao inserir produto: " + e.getMessage());
      return false;
    }

  }

  @Override
  public boolean atualizar(Produto p){
    
    String sql = """
      UPDATE produto
      SET   valor_unitario = ?,
            categoria = ?,
            qtd_estoque = ?,
            desconto = ?,
            descricao = ?
      WHERE codigo = ?
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){

      stm.setDouble(1, p.getValorUnitario());
      stm.setString(2, p.getCategoria());
      stm.setInt(3, p.getQtdEstoque());
      stm.setDouble(4, p.getDesconto());
      stm.setString(5, p.getDescricao());
      stm.setLong(6, p.getCodigo());

      stm.executeUpdate();

      return true;

    }catch(Exception e){
      System.out.println("Erro ao atualizar produto: " + e.getMessage());
      return false;
    }

  }

  @Override
  public boolean excluir(Long id){
    
    String sql = """
      DELETE 
      FROM produto
      WHERE codigo = ?
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){
      
      stm.setLong(1, id);

      stm.executeUpdate();
      
      return true;

    }catch(Exception e){
      System.out.println("Erro ao excluir produto: " + e.getMessage());
      return false;
    }

  }

  @Override
  public Produto buscarPorId(Long id){
    return null;
  }

  public LinkedList<Produto> pesquisarPorNome(String texto){
    
    LinkedList<Produto> lista = new LinkedList<>();

    String sql = """
      SELECT *
      FROM produto
      WHERE descricao LIKE '%?%'
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){

      stm.setString(1, texto);

      ResultSet rs = stm.executeQuery();
      
      while(rs.next()){
        
        Produto p = new Produto(); 
        
        p.setIdVendedor(rs.getLong("id_vendedor"));
        p.setCodigo(rs.getLong("codigo"));
        p.setValorUnitario(rs.getDouble("valor_unitario"));
        p.setCategoria(rs.getString("categoria"));
        p.setQtdEstoque(rs.getInt("qtd_estoque"));
        p.setDesconto(rs.getDouble("desconto"));
        p.setDescricao(rs.getString("descricao"));

        lista.add(p);

      }

      return lista;


    }catch(Exception e){
      System.out.println("Erro ao pesquisar por nome: " + e.getMessage());
    }

    return lista;

  }




}
