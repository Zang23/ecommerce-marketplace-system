package edu.marketplace.entity;

import java.time.LocalDate;

public class UsuarioCartao {

  private String numeroCartao;
  private Long idComprador;
  private LocalDate validade;
  private String cvv;
  private String nomeTitular;

  public UsuarioCartao(String numeroCartao, Long idComprador, LocalDate validade, String cvv, String nomeTitular){

    this.numeroCartao = numeroCartao;
    this.idComprador = idComprador;
    this.validade = validade;
    this.cvv = cvv;
    this.nomeTitular = nomeTitular;

  }

  public String getNumeroCartao() {
    return numeroCartao;
  }

  public void setNumeroCartao(String numeroCartao) {
    this.numeroCartao = numeroCartao;
  }

  public Long getIdComprador() {
    return idComprador;
  }

  public void setIdComprador(Long idComprador) {
    this.idComprador = idComprador;
  }

  public LocalDate getValidade() {
    return validade;
  }

  public void setValidade(LocalDate validade) {
    this.validade = validade;
  }

  public String getCvv() {
    return cvv;
  }
  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public String getNomeTitular() {
    return nomeTitular;
  }

  public void setNomeTitular(String nomeTitular) {
    this.nomeTitular = nomeTitular;
  }

}
