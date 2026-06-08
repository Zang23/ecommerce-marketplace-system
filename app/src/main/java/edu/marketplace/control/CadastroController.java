package edu.marketplace.control;

import java.sql.Connection;
import java.sql.SQLException;

import edu.marketplace.dao.CompradorDAO;
import edu.marketplace.dao.ConnectionFactory;
import edu.marketplace.dao.VendedorDAO;
import edu.marketplace.entity.Comprador;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Resumo do serviço
// Itens:
// Validar campos (ver se o usuário já existe, formato do email, senha válida)
// Cadastrar clientes (Tanto comprador como Vendedor)

public class CadastroController {

  // Resuminho: Primeiro valida os campos, se tiverem certos ele pede pro DAO
  // salvar o cliente no banco e depois disso solta uma mensagem falando noq deu
  public final StringProperty nome = new SimpleStringProperty(this, "nome");
  public String cadastrar(Comprador comprador, boolean ehVendedor) {

    // Função pra ver se o email é válido
    if (emailValido(comprador.getEmail()) == false) {
        return "Email inválido";
    }

    // Função pra ver se a senha é válida
    if (senhaValida(comprador.getSenha()) == false) {
        return "A senha precisa ter pelo menos 6 caracteres";
    }

    // Com tudo certo aí eu peço pro DAO salvar o cliente no banco, e depois disso solto uma mensagem falando noq deu
    try {
        // Aqui cria a ÙNICA CONEXÃO
        Connection conexao = ConnectionFactory.getConnection();
        CompradorDAO compradorDao = new CompradorDAO(conexao);
        VendedorDAO vendedorDao = new VendedorDAO(conexao);

        // Usa a função de verificar email
        if (compradorDao.existePorEmail(comprador.getEmail())) {
            conexao.close();
            return "Já existe uma conta com esse email";
        }

        // Mudei pra ver qual DAO que vai colocar / cadastrar o usuário
        boolean inseriu;
        if (ehVendedor == true) {
            inseriu = vendedorDao.inserir(comprador);
        } else {
            inseriu = compradorDao.inserir(comprador);
        }

        conexao.close();

        // O "inseriu" devolve true se deu tudo certo
        if (inseriu == true) {
            return "Cadastrado com sucesso";
        } else {
            return "Erro ao cadastrar, tente de novo";
        }

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao cadastrar, tente de novo";
    }
  }

  // Função que verifica se o usuário já está cadastrado
  public boolean usuarioExiste(String email) {

    try {
        Connection conexao = ConnectionFactory.getConnection();
        CompradorDAO dao = new CompradorDAO(conexao);

        // Ach oque verificar por email é o melhor, ai faz uma função que só
        // confere se tem arroba e ponto ou algo assim simples p testar
        // Como vendedor HERDA do comprador, só precisa verificar no DAO de comprador mesmo e gg
        boolean jaExiste = dao.existePorEmail(email);
        conexao.close();
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
