package edu.marketplace.dao;

import java.sql.Connection;

import edu.marketplace.entity.Comprador;
public class VendedorDAO implements CrudDAO<Comprador, Long>{

  private Connection con;
  public VendedorDAO (Connection con){
    this.con = con;
  }

  @Override
  public boolean inserir(Comprador v){
    
    return false;

  }

  @Override
  public boolean atualizar(Comprador v){

    return false;

  }

  @Override
  public boolean excluir(Long id){
  
    return false;
  } 

  @Override
  public Comprador buscarPorId(Long id){
    return null;
  }
  


}
