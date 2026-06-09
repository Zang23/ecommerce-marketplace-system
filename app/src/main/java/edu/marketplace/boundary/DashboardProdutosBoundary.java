package edu.marketplace.boundary;

import edu.marketplace.entity.Produto;
import edu.marketplace.control.DashboardProdutosController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DashboardProdutosBoundary {

    public static Scene criarCena(Stage stage) {
        DashboardProdutosController controller = new DashboardProdutosController();

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2b2b2b;");

        //  BARRA SUPERIOR (NAVBAR) 
        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #1a1a1a;");
        topBar.setMinHeight(50);
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setPadding(new Insets(5, 20, 5, 20));
        root.setTop(topBar);

        //  CONTEÚDO CENTRAL 
        VBox centerBox = new VBox(30);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setPadding(new Insets(40, 60, 40, 60));

        Label titleLabel = new Label("Dashboard de produtos");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 28px; -fx-font-weight: bold;");

        //  CONFIGURAÇÃO DA TABLEVIEW 
        TableView<Produto> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(400);

        // Colunas de Texto (O nome na PropertyValueFactory deve ser exatamente igual ao nome da variável na classe Produto)
        TableColumn<Produto, String> colCodigo = new TableColumn<>("Código");
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        TableColumn<Produto, String> colDescricao = new TableColumn<>("Descrição");
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        TableColumn<Produto, Integer> colQtd = new TableColumn<>("Quantidade");
        colQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        TableColumn<Produto, Double> colValor = new TableColumn<>("Valor");
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        TableColumn<Produto, String> colCategoria = new TableColumn<>("Categoria");
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        TableColumn<Produto, Double> colDesconto = new TableColumn<>("Desconto");
        colDesconto.setCellValueFactory(new PropertyValueFactory<>("desconto"));

        // Coluna de Ação: DELETAR
        TableColumn<Produto, Void> colDeletar = new TableColumn<>("");
        colDeletar.setMaxWidth(50);
        colDeletar.setMinWidth(50);
        colDeletar.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("🗑");
            {
                btn.getStyleClass().add("btn-acao");
                btn.setOnAction(event -> {
                    
                    Produto produtoSelecionado = getTableView().getItems().get(getIndex());
                    controller.excluirProduto(produtoSelecionado);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) setGraphic(null);
                else setGraphic(btn);
            }
        });

        // Coluna de Ação: EDITAR
        TableColumn<Produto, Void> colEditar = new TableColumn<>("");
        colEditar.setMaxWidth(50);
        colEditar.setMinWidth(50);
        colEditar.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("✏");
            {
                btn.getStyleClass().add("btn-acao");
                btn.setOnAction(event -> {

                    Produto produtoSelecionado = getTableView().getItems().get(getIndex());
                    controller.editarProduto(produtoSelecionado);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) setGraphic(null);
                else setGraphic(btn);
            }
        });

   
        table.getColumns().addAll(colCodigo, colDescricao, colQtd, colValor, colCategoria, colDesconto, colDeletar, colEditar);

        table.setItems(controller.getListaProdutos());

        centerBox.getChildren().addAll(titleLabel, table);
        root.setCenter(centerBox);

        Scene scene = new Scene(root, 1000, 700);
        DashboardProdutosBoundary.class.getResource("cadastro.css");

        return scene;
    }
}
