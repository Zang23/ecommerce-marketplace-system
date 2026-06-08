package edu.marketplace.dao;

import edu.marketplace.entity.UsuarioCartao;

import java.util.LinkedList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CartaoDAO implements CrudDAO<UsuarioCartao, String>{

  private Connection con;

  private LinkedList<UsuarioCartao> lista = new LinkedList<>();
  public CartaoDAO(Connection con){
    this.con = con;
  }

  @Override
  public boolean inserir(UsuarioCartao c){
    return false;
  }

  @Override
  public boolean atualizar(UsuarioCartao c){
    return false;
  }

  @Override
  public boolean excluir(String id){
    return false;
  }

  @Override
  public UsuarioCartao buscarPorId(Long id){
    return null;
  }
  
  public LinkedList<UsuarioCartao> listarPorComprador(Long idUsuario){
    
    String sql = """
      SELECT  *
      FROM usuario_cartao
      WHERE id_comprador = ?
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){
    
      stm.setLong(1, idUsuario);

      ResultSet rs = stm.executeQuery();

      while(rs.next()){
        
        UsuarioCartao c = new UsuarioCartao();

        c.setNumeroCartao(rs.getString("numero_cartao"));
        c.setIdComprador(rs.getLong("id_comprador"));
        c.setValidade(rs.getDate("validade").toLocalDate());
        c.setCvv(rs.getString("cvv"));
        c.setNomeTitular(rs.getString("nome_titular"));

        lista.add(c);        

      }

      return lista;

    }catch(Exception e){
      System.out.println("Erro ao listar os cartoes do usuario: " + e.getMessage());

      return lista;
    }
  }


}
