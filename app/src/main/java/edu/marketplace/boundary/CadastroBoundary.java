package edu.marketplace.boundary;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CadastroBoundary {

    // Em vez do 'start()', criamos um método que devolve a Scene pronta
    public static Scene criarCena(Stage stage) {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #2b2b2b;");

        VBox card = new VBox(15);
        card.getStyleClass().add("login-card");
        card.setMaxSize(400, 550);
        card.setAlignment(Pos.TOP_CENTER);
        card.setPadding(new Insets(30, 40, 30, 40));

       // Título
        Label titleLabel = new Label("Criar conta");
        titleLabel.getStyleClass().add("title");
        VBox.setMargin(titleLabel, new Insets(0, 0, 10, 0));

        // --- SELEÇÃO DE TIPO DE CONTA ---
        Label tipoContaLabel = new Label("Tipo de conta");
        tipoContaLabel.setMaxWidth(Double.MAX_VALUE);
        tipoContaLabel.getStyleClass().add("label");

        // ToggleButtons funcionam como botões normais, mas ficam "pressionados"
        ToggleButton btnComprador = new ToggleButton("Comprador");
        ToggleButton btnVendedor = new ToggleButton("Vendedor");
        

        btnComprador.setMaxWidth(Double.MAX_VALUE);
        btnVendedor.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(btnComprador, Priority.ALWAYS);
        HBox.setHgrow(btnVendedor, Priority.ALWAYS);

        // Agrupando para que apenas um fique selecionado por vez
        ToggleGroup tipoContaGroup = new ToggleGroup();
        btnComprador.setToggleGroup(tipoContaGroup);
        btnVendedor.setToggleGroup(tipoContaGroup);
        btnComprador.setSelected(true); 

        HBox tipoContaBox = new HBox(15, btnComprador, btnVendedor);

        
        // Campo Nome
        Label nomeLabel = new Label("Nome completo");
        nomeLabel.setMaxWidth(Double.MAX_VALUE);
        nomeLabel.getStyleClass().add("label");
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");
        VBox nomeBox = new VBox(5, nomeLabel, nomeField);

        
        Label documentoLabel = new Label("CPF");
        documentoLabel.setMaxWidth(Double.MAX_VALUE);
        documentoLabel.getStyleClass().add("label");
        TextField documentoField = new TextField();
        documentoField.setPromptText("000.000.000-00");
        VBox documentoBox = new VBox(5, documentoLabel, documentoField);

        // O Listener que escuta os botões de tipo de conta
        tipoContaGroup.selectedToggleProperty().addListener((obs, antigo, novo) -> {
            if (novo == null) {
                antigo.setSelected(true); // Impede de desmarcar tudo
            } else if (novo == btnComprador) {
                documentoLabel.setText("CPF");
                documentoField.setPromptText("000.000.000-00");
                documentoField.clear(); // Limpa o campo ao trocar
            } else if (novo == btnVendedor) {
                documentoLabel.setText("CNPJ");
                documentoField.setPromptText("00.000.000/0001-00");
                documentoField.clear(); // Limpa o campo ao trocar
            }
        });

        // Limita o tamanho e aceita só números
        documentoField.textProperty().addListener((obs, valorAntigo, valorNovo) -> {
            // Remove tudo que NÃO for número (letras, espaços, símbolos)
            String apenasNumeros = valorNovo.replaceAll("[^\\d]", "");
            
            int tamanhoMaximo = btnComprador.isSelected() ? 11 : 14;

            if (apenasNumeros.length() > tamanhoMaximo) {
                apenasNumeros = apenasNumeros.substring(0, tamanhoMaximo);
            }
            
    
            if (!valorNovo.equals(apenasNumeros)) {
                documentoField.setText(apenasNumeros);
            }
        });

        // Campo E-mail
        Label emailLabel = new Label("E-mail");
        emailLabel.setMaxWidth(Double.MAX_VALUE);
        emailLabel.getStyleClass().add("label");
        TextField emailField = new TextField();
        emailField.setPromptText("example@email.com");
        VBox emailBox = new VBox(5, emailLabel, emailField);

        // Campo Senha
        Label senhaLabel = new Label("Senha");
        senhaLabel.setMaxWidth(Double.MAX_VALUE);
        senhaLabel.getStyleClass().add("label");
        PasswordField senhaField = new PasswordField();
        senhaField.setPromptText("********");
        VBox senhaBox = new VBox(5, senhaLabel, senhaField);

        // Campo Confirmar Senha
        Label senha2Label = new Label("Confirmar senha"); // Na imagem está Senha duas vezes
        senha2Label.setMaxWidth(Double.MAX_VALUE);
        senha2Label.getStyleClass().add("label");
        PasswordField senha2Field = new PasswordField();
        senha2Field.setPromptText("********");
        VBox senha2Box = new VBox(5, senha2Label, senha2Field);

        // Botão Cadastrar
        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.setMaxWidth(Double.MAX_VALUE);
        btnCadastrar.getStyleClass().add("btn-entrar");
        VBox.setMargin(btnCadastrar, new Insets(15, 0, 0, 0));

        // Adicionando os elementos do formulário no card
        card.getChildren().addAll(
            titleLabel,
            tipoContaLabel,
            tipoContaBox,
            nomeBox,
            documentoBox,
            emailBox,
            senhaBox,
            senha2Box,
            btnCadastrar
        );
        root.getChildren().add(card);

        Scene scene = new Scene(root, 1000, 700);
        
        // Carrega o CSS existente no projeto
        scene.getStylesheets().add(CadastroBoundary.class.getResource("/css/cadastro.css").toExternalForm());

        return scene;
    }
}