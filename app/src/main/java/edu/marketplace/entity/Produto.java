package edu.marketplace.entity;


public class Produto {

  private Long codigo;
  private Long idVendedor;
  private double valorUnitario;
  private String categoria;
  private int qtdEstoque;
  private double desconto;
  private String descricao;

  public Produto(
    Long codigo, 
    Long idVendedor, 
    double valorUnitario, 
    String categoria,
    int qtdEstoque,
    double desconto,
    String descricao
  ){
    this.codigo = codigo;
    this.idVendedor = idVendedor;
    this.valorUnitario = valorUnitario;
    this.categoria = categoria;
    this.qtdEstoque = qtdEstoque;
    this.desconto = desconto;
    this.descricao = descricao;
  }

  public Produto (){
    super();
  }

  public Long getCodigo() {
    return codigo;
  }

  public Long getIdVendedor() {
    return idVendedor;
  }

  public void setIdVendedor(Long idVendedor) {
    this.idVendedor = idVendedor;
  }

  public double getValorUnitario() {
    return valorUnitario;
  }

  public void setValorUnitario(double valorUnitario) {
    this.valorUnitario = valorUnitario;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public void setQtdEstoque(int qtdEstoque) {
    this.qtdEstoque = qtdEstoque;
  }

  public int getQtdEstoque() {
    return qtdEstoque;
  }

  public double getDesconto() {
    return desconto;
  }

  public void setDesconto(double desconto) {
    this.desconto = desconto;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }


}
