package edu.marketplace.dao;

import edu.marketplace.entity.Comprador;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CompradorDAO implements CrudDAO<Comprador> {

  private Connection con;

  public CompradorDAO(Connection con){
    this.con = con;
  }


@Override
 public boolean inserir (Comprador c){

      String sql = """
        INSERT INTO comprador
          (email, senha, nome, telefone, endereco_logradouro, endereco_numero, endereco_cep, endereco_cidade)
        VALUES 
          (?, ?, ?, ?, ?, ?, ?, ?)
        """;


    try(PreparedStatement stm = con.prepareStatement(sql)){

      stm.setString(1, c.getEmail());
      stm.setString(2, c.getSenha());
      stm.setString(3, c.getNome());
      stm.setString(4, c.getTelefone());
      stm.setString(5, c.getEnderecoLogradouro());
      stm.setInt(6, c.getEnderecoNumero());
      stm.setString(7, c.getEnderecoCep());
      stm.setString(8, c.getEnderecoCidade());

      stm.executeUpdate();

      return true;
      
    } catch (Exception e) {
      System.out.println("Erro ao inserir comprador: " + e.getMessage());
      return false;
    }

 }

  @Override
  public boolean atualizar(Comprador c){
    
    String sql = """
      UPDATE comprador
      SET 
        email = ?,
        senha = ?,
        nome = ?,
        telefone = ?,
        endereco_logradouro = ?
        endereco_numero = ?
        endereco_cep = ?
        endereco_cidade = ?
      WHERE id = ?
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){

      stm.setString(1, c.getEmail());
      stm.setString(2, c.getSenha());
      stm.setString(3, c.getNome());
      stm.setString(4, c.getTelefone());
      stm.setString(5, c.getEnderecoLogradouro());
      stm.setInt(6, c.getEnderecoNumero());
      stm.setString(7, c.getEnderecoCep());
      stm.setString(8, c.getEnderecoCidade());
      stm.setLong(9, c.getId());

      stm.executeUpdate();

      return true;

    }catch(Exception e){
      System.out.println("Erro ao atualizar comprador: " + e.getMessage());
      return false;
    }
    
  }

  @Override
  public boolean excluir(Long id){
    
    String sql = """
      DELETE FROM comprador
      WHERE id = ?
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){
        
      stm.setLong(1, id);

      stm.executeUpdate();

      return true;

    }catch(Exception e){
      System.out.println("Erro ao deletar comprador: {" + id + "}" + e.getMessage());
    }

    return false;
  }

  @Override 
  public Comprador buscarPorId(Long id){
    return null;
  }
  

  

  
}
