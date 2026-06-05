package edu.marketplace.entity;

public class Comprador{

  private Long id;
  private String nome;
  private String email;
  private String senha;
  private String telefone;
  private String enderecoLogradouro;
  private String enderecoNumero;
  private String enderecoCep;
  private String enderecoCidade;

  public Comprador(Long id, String nome, String email, String senha, String telefone, String enderecoLogradouro, String enderecoNumero, String enderecoCep, String enderecoCidade){

    this.id = id;
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.telefone = telefone;
    this.enderecoLogradouro = enderecoLogradouro;
    this.enderecoNumero = enderecoNumero;
    this.enderecoCep = enderecoCep;
    this.enderecoCidade = enderecoCidade;

  }
  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }


  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getEnderecoLogradouro() {
    return enderecoLogradouro;
  }
  public void setEnderecoLogradouro(String enderecoLogradouro) {
    this.enderecoLogradouro = enderecoLogradouro;
  }

  public String getEnderecoNumero() {
    return enderecoNumero;
  }

  public void setEnderecoNumero(String enderecoNumero) {
    this.enderecoNumero = enderecoNumero;
  }

  public String getEnderecoCep() {
    return enderecoCep;
  }

  public void setEnderecoCep(String enderecoCep) {
    this.enderecoCep = enderecoCep;
  }

  public String getEnderecoCidade() {
    return enderecoCidade;
  }

  public void setEnderecoCidade(String enderecoCidade) {
    this.enderecoCidade = enderecoCidade;
  }






}
