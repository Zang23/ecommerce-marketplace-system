package edu.marketplace.dao;

import edu.marketplace.entity.Comprador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompradorDAO implements CrudDAO<Comprador, Long> {

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
 
  public Comprador buscarPorEmailESenha(String email, String senha){
  
    String sql = """
      SELECT  * 
      FROM comprador
      WHERE email = ?
    """;

    Comprador c = new Comprador();


    try(PreparedStatement stm = con.prepareStatement(sql)){

      stm.setString(1, email);

      ResultSet rs = stm.executeQuery();

      while(rs.next()){

        
        c.setId(rs.getLong("id"));
        c.setEmail(rs.getString("email"));
        c.setSenha(rs.getString("senha"));
        c.setTelefone(rs.getString("telefone"));
        c.setNome(rs.getString("nome"));
        c.setEnderecoLogradouro(rs.getString("endereco_logradouro"));
        c.setEnderecoNumero(rs.getInt("endereco_numero"));
        c.setEnderecoCep(rs.getString("endereco_cep"));
        c.setEnderecoCidade(rs.getString("endereco_cidade"));

        System.out.println("Comprador encontrado com sucesso!");
        return c;

      }

    }catch(Exception e){
      System.out.println("Erro ao fazer a busca por email e senha: "  + e.getMessage());
    }

    System.out.println("Comprador nao encontrado!");
    return c;

  }

  public boolean ehVendedor(Long id){

    String sql = """
      SELECT COUNT(*) AS quantidade
      FROM vendedor
      WHERE id_comprador = ?
    """;

    try(PreparedStatement stm = con.prepareStatement(sql)){
      
      stm.setLong(1,id);

      ResultSet rs = stm.executeQuery();

      if(rs.next()){
        
        long qtd = rs.getLong("quantidade");
        if(qtd > 0){
          return true;
        }

      }

      return false;

    }catch(Exception e){
      System.out.println("Erro ao verificar se era um vendedor: " + e.getMessage());
      return false;
    }


  }

  

  
}
