package edu.marketplace.boundary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CartaoBoundary {

    public static Scene criarCena(Stage stage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2b2b2b;");

        HBox topBar = new HBox();
        topBar.setStyle("-fx-background-color: #1a1a1a;"); 
        topBar.setMinHeight(50); 
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setPadding(new Insets(5, 20, 5, 20));
        root.setTop(topBar);

        StackPane centerPane = new StackPane();
        centerPane.setAlignment(Pos.CENTER); 

        VBox card = new VBox(15); 
        card.getStyleClass().add("login-card");
        card.setMaxSize(450, 420); 
        card.setAlignment(Pos.CENTER); 
        card.setPadding(new Insets(30, 40, 30, 40));

        Label titleLabel = new Label("Cadastrar cartão");
        titleLabel.getStyleClass().add("title");
        VBox.setMargin(titleLabel, new Insets(0, 0, 10, 0));

        Label numLabel = new Label("Número do cartão");
        numLabel.setMaxWidth(Double.MAX_VALUE);
        numLabel.getStyleClass().add("label");
        TextField numField = new TextField();
        numField.setPromptText("1234.5678.9012.3456");
        VBox numBox = new VBox(5, numLabel, numField);

        Label valLabel = new Label("Validade");
        valLabel.setMaxWidth(Double.MAX_VALUE);
        valLabel.getStyleClass().add("label");
        TextField valField = new TextField();
        valField.setPromptText("MM/AA");
        VBox valBox = new VBox(5, valLabel, valField);
        HBox.setHgrow(valBox, Priority.ALWAYS); 

        Label cvvLabel = new Label("CVV");
        cvvLabel.setMaxWidth(Double.MAX_VALUE);
        cvvLabel.getStyleClass().add("label");
        TextField cvvField = new TextField();
        cvvField.setPromptText("123");
        VBox cvvBox = new VBox(5, cvvLabel, cvvField);
        HBox.setHgrow(cvvBox, Priority.ALWAYS); 

        HBox rowValCvvBox = new HBox(15, valBox, cvvBox);

        Label nomeLabel = new Label("Nome do titular");
        nomeLabel.setMaxWidth(Double.MAX_VALUE);
        nomeLabel.getStyleClass().add("label");
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome no cartão");
        VBox nomeBox = new VBox(5, nomeLabel, nomeField);

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.setMaxWidth(Double.MAX_VALUE);
        btnCadastrar.getStyleClass().add("btn-entrar");
        VBox.setMargin(btnCadastrar, new Insets(15, 0, 0, 0));

        // Validação do CVV: aceita apenas números e limita a 4 dígitos
        cvvField.textProperty().addListener((obs, antigo, novo) -> {
            String apenasNumeros = novo.replaceAll("[^\\d]", "");
            if (apenasNumeros.length() > 4) {
                apenasNumeros = apenasNumeros.substring(0, 4);
            }
            if (!novo.equals(apenasNumeros)) {
                cvvField.setText(apenasNumeros);
            }
        });

        card.getChildren().addAll(
            titleLabel,
            numBox,
            rowValCvvBox, 
            nomeBox,
            btnCadastrar
        );

        centerPane.getChildren().add(card);
        root.setCenter(centerPane); 

        Scene scene = new Scene(root, 1000, 700);
            scene.getStylesheets().add(CartaoBoundary.class.getResource("/css/cadastro.css").toExternalForm());

        return scene;
    }
}
