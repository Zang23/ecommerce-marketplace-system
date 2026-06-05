package edu.marketplace.entity;

import java.time.LocalDate;

public class Pedido{

  private Long id;
  private Long idComprador;
  private double valorTotal;
  private LocalDate dataFinalizacao; 
  private String status;

  public Pedido(){
    super();
  }

  public Pedido(
    Long id,
    Long idComprador,
    double valorTotal,
    LocalDate dataFinalizacao,
    String status
  ){
    this.id = id;
    this.idComprador = idComprador;
    this.valorTotal = valorTotal;
    this.dataFinalizacao = dataFinalizacao;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public Long getIdComprador() {
    return idComprador;
  }

  public void setIdComprador(Long idComprador) {
    this.idComprador = idComprador;
  }

  public double getValorTotal() {
    return valorTotal;
  }

  public void setValorTotal(double valorTotal) {
    this.valorTotal = valorTotal;
  }

  public LocalDate getDataFinalizacao() {
    return dataFinalizacao;
  }

  public void setDataFinalizacao(LocalDate dataFinalizacao) {
    this.dataFinalizacao = dataFinalizacao;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
