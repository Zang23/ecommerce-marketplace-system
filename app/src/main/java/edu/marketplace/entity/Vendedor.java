package edu.marketplace.entity;
public class Vendedor {

  private Long idComprador;
  public Vendedor(){
    super();
  }
  public Vendedor (Long idComprador){
    this.idComprador = idComprador;
  }

  public Long getIdComprador() {
    return idComprador;
  }

  public void setIdComprador(Long idComprador) {
    this.idComprador = idComprador;
  } 

}
