package edu.marketplace.dao;

public interface CrudDAO<T, ID> {

  boolean inserir(T entidade);

  boolean atualizar(T entidade);

  boolean excluir(ID id);

  T buscarPorId(Long id);

}
