package edu.marketplace.control;

import java.sql.SQLException;

import edu.marketplace.dao.CompradorDAO;
import edu.marketplace.entity.Comprador;

// Resumo do serviço
// Itens:
// Validar campos (ver se o usuário já existe, formato do email, senha válida)
// Cadastrar clientes  

public class CadastroController {

  // Resuminho: Primeiro valida os campos, se tiverem certos ele pede pro DAO
  // salvar o cliente no banco e depois disso solta uma mensagem falando noq deu
  public String cadastrar(Comprador comprador, boolean ehVendedor) {

    // Função pra ver se o email é válido
    if (emailValido(comprador.getEmail()) == false) {
        return "Email inválido";
    }

    // Função pra ver se a senha é válida
    if (senhaValida(comprador.getSenha()) == false) {
        return "A senha precisa ter pelo menos 6 caracteres";
    }

    // Função pra ver se já existe algúem com aquele email
    if (usuarioExiste(comprador.getEmail())) {
        return "Já existe uma conta com esse email";
    }

    // Se chegou aqui vai pro DAO.
    try {
        // Aqui abre a conexão, ela vai pro DAO, Thiago pelo oq eu pesquisei
        // vocÊ vai precisar fazer no seu DAO uma função pra inserir o comprador
        CompradorDAO dao = new CompradorDAO();

        // Passando o objeto todo, se precisar mudar só avisar (Boolean pra saber se é vendedor ou não)
        dao.inserir(comprador, ehVendedor);

        return "Cadastrado com sucesso";

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao cadastrar, tente de novo";
    }
  }

  // Função que verifica se o usuário já está cadastrado
  public boolean usuarioExiste(String email) {

    try {
        CompradorDAO dao = new CompradorDAO();

        // Ach oque verificar por email é o melhor, ai faz uma função que só
        // confere se tem arroba e ponto ou algo assim simples p testar
        boolean jaExiste = dao.existePorEmail(email);
        return jaExiste;

    } catch (SQLException erro) {
        erro.printStackTrace();
        return false;
    }
  }

  // Função pra ver se o email é valido (verifica só o arroba e o ponto, se quiserem que eu melhore só avisar)
  public boolean emailValido(String email) {
    if (email == null) {
        return false;
    }
    return email.contains("@") && email.contains(".");
  }

  // Função pra ver se a senha tem pelo menos 6 caracteres
  public boolean senhaValida(String senha) {
        // mesma coisa do email, se quiserem mais regrinhas só avisar
    if (senha == null) {
        return false;
    }
    return senha.length() >= 6;
  }
}