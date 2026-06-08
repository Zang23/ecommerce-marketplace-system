package edu.marketplace.dao;

public interface CrudDAO<T> {

  boolean inserir(T entidade);

  boolean atualizar(T entidade);

  boolean excluir(Long id);

  T buscarPorId(Long id);

}
