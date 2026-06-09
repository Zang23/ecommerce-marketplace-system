package edu.marketplace.control;

import java.sql.Connection;
import java.sql.SQLException;

import edu.marketplace.dao.CompradorDAO;
import edu.marketplace.dao.ConnectionFactory;
import edu.marketplace.entity.Comprador;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Resumo do serviço
// Itens:
// Autenticar usuário (ver se o login e a senha existem no banco, deixar entrar ou dar erro)
// Fazer o logout
// Saber se quem logou é comprador ou vendedor (pra abrir a tela certa)

public class LoginController {

  // Aqui eu guardo quem ta logado e se ele é vendedor, ai as outras telas conseguem ver isso depois
  private static Comprador usuarioLogado;
  private static boolean vendedorLogado;
  
  	public final StringProperty emailProperty = new SimpleStringProperty(this, "email");
	public final StringProperty senhaProperty = new SimpleStringProperty(this, "senha");

  // Resuminho: confere o email e a senha no banco. Se tiver certo deixa entrar e guarda quem logou,
  // se não der certo solta uma mensagem de erro
  public String autenticar() {

    try {
        // Aqui cria a conexão
        Connection conexao = ConnectionFactory.getConnection();
        //Pelo oq pesquisei aqui não precisa diferenciar o DAO porque como vendedor herda do comprador, ele consegue achar
        CompradorDAO dao = new CompradorDAO(conexao);

        // Thiago,aqui vai precisar de uma função no DAO pra achar o comprador pelo email e a senha (vem null se não achar)
        
        Comprador comprador = dao.buscarPorEmailESenha(emailProperty.get(), senhaProperty.get());

        // Se veio null é porque não tem ninguém com esse email e senha, então ta errado
        if (comprador == null || comprador.getId() == null) {
            conexao.close();
            return "Email ou senha incorretos";
        }

        // Se der certo guarda quem logou e pergunta pro DAO se ele é vendedor (Thiago, precisa de uma função pra isso tbm, fica em paz pra apagar esses comentários depois que fizer)
        usuarioLogado = comprador;
        vendedorLogado = dao.ehVendedor(comprador.getId());

        conexao.close();
        return "Login feito com sucesso";

    } catch (SQLException erro) {
        erro.printStackTrace();
        return "Erro ao fazer login, tente de novo";
    }
  }

  // Função que faz o logout, ela EXTERMINA (desloga) quem tava logado
  public void logout() {
    usuarioLogado = null;
    vendedorLogado = false;
  }

  // Função que devolve quem ta logado agora (vem null se ninguém logou)
  public static Comprador getUsuarioLogado() {
    return usuarioLogado;
  }

  // Função que diz se quem ta logado é vendedor, a tela usa isso pra abrir a tela certa
  public static boolean isVendedorLogado() {
    return vendedorLogado;
  }
}