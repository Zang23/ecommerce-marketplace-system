package edu.marketplace.entity;

public class ProdutoCarrinho {

  private Long codProduto;
  private Long idPedido;
  private int qtdCarrinho;

  public ProdutoCarrinho(){
    super();
  }

  public ProdutoCarrinho(Long codProduto, Long idPedido, int qtdCarrinho){

    this.codProduto = codProduto;
    this.idPedido = idPedido;
    this.qtdCarrinho = qtdCarrinho;

  }

  public Long getCodProduto() {
    return codProduto;
  }

  public void setCodProduto(Long codProduto) {
    this.codProduto = codProduto;
  }

  public Long getIdPedido() {
    return idPedido;
  }

  public void setIdPedido(Long idPedido) {
    this.idPedido = idPedido;
  }

  public int getQtdCarrinho() {
    return qtdCarrinho;
  }

  public void setQtdCarrinho(int qtdCarrinho) {
    this.qtdCarrinho = qtdCarrinho;
  }


}


