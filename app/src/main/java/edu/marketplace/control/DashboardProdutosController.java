package edu.marketplace.control;
import edu.marketplace.entity.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DashboardProdutosController {
    public void editarProduto(Produto produto) {
        // Lógica para abrir a tela de edição do produto
        System.out.println("Editando produto: " + produto.getDescricao());
    }

    public void excluirProduto(Produto produto) {
        // Lógica para excluir o produto
        System.out.println("Excluindo produto: " + produto.getDescricao());
    }

    public void adicionarProduto() {
        // Lógica para abrir a tela de cadastro de um novo produto
        System.out.println("Adicionando novo produto");
    }

    public ObservableList<Produto> getListaProdutos() {
        // Lógica para obter a lista de produtos (banco de dados)
        return FXCollections.observableArrayList();
    }

}
