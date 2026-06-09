package edu.marketplace.boundary;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import edu.marketplace.control.CadastroController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CadastroBoundary {

    // Em vez do 'start()', criamos um método que devolve a Scene pronta
    public static Scene criarCena(Stage stage) {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #2b2b2b;");
		CadastroController controller = new CadastroController();

        VBox card = new VBox(15);
        card.getStyleClass().add("login-card");
        card.setPrefWidth(400);
        card.setMaxWidth(400);
        card.setAlignment(Pos.TOP_CENTER);
        card.setPadding(new Insets(30, 40, 30, 40));

        Label titleLabel = new Label("Criar conta");
        titleLabel.getStyleClass().add("title");
        VBox.setMargin(titleLabel, new Insets(0, 0, 10, 0));

        // Tipo de Conta (Mantido)
        Label tipoContaLabel = new Label("Tipo de conta");
        tipoContaLabel.setMaxWidth(Double.MAX_VALUE);
        tipoContaLabel.getStyleClass().add("label");

        ToggleButton btnComprador = new ToggleButton("Comprador");
        ToggleButton btnVendedor = new ToggleButton("Vendedor");
        btnComprador.getStyleClass().add("meu-toggle-button");
        btnVendedor.getStyleClass().add("meu-toggle-button");
        
        btnComprador.setMaxWidth(Double.MAX_VALUE);
        btnVendedor.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(btnComprador, Priority.ALWAYS);
        HBox.setHgrow(btnVendedor, Priority.ALWAYS);

        ToggleGroup tipoContaGroup = new ToggleGroup();
        btnComprador.setToggleGroup(tipoContaGroup);
        btnVendedor.setToggleGroup(tipoContaGroup);
        btnComprador.setSelected(true); 

        HBox tipoContaBox = new HBox(15, btnComprador, btnVendedor);

        // Nome
        Label nomeLabel = new Label("Nome completo");
        nomeLabel.setMaxWidth(Double.MAX_VALUE);
        nomeLabel.getStyleClass().add("label");
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");
        VBox nomeBox = new VBox(5, nomeLabel, nomeField);

        
        // CEP
        Label cepLabel = new Label("CEP");
        cepLabel.setMaxWidth(Double.MAX_VALUE);
        cepLabel.getStyleClass().add("label");
        TextField cepField = new TextField();
        cepField.setPromptText("00000-000");
        VBox cepBox = new VBox(5, cepLabel, cepField);

        // Validação do CEP (Aceita só números e limita a 8)
        cepField.textProperty().addListener((obs, antigo, novo) -> {
            String apenasNumeros = novo.replaceAll("[^\\d]", "");
            if (apenasNumeros.length() > 8) {
                apenasNumeros = apenasNumeros.substring(0, 8);
            }
            if (!novo.equals(apenasNumeros)) {
                cepField.setText(apenasNumeros);
            }
        });

        // Rua e Número (Lado a Lado)
        Label ruaLabel = new Label("Rua");
        ruaLabel.setMaxWidth(Double.MAX_VALUE);
        ruaLabel.getStyleClass().add("label");
        TextField ruaField = new TextField();
        ruaField.setPromptText("Nome da rua");
        VBox ruaBox = new VBox(5, ruaLabel, ruaField);
        HBox.setHgrow(ruaBox, Priority.ALWAYS); 

        Label numLabel = new Label("Número");
        numLabel.setMaxWidth(Double.MAX_VALUE);
        numLabel.getStyleClass().add("label");
        TextField numField = new TextField();
        numField.setPromptText("123");
        VBox numBox = new VBox(5, numLabel, numField);
        numBox.setPrefWidth(90); // Fixa a caixinha do número menorzinha

        HBox ruaNumBox = new HBox(15, ruaBox, numBox);

        // E-mail
        Label emailLabel = new Label("E-mail");
        emailLabel.setMaxWidth(Double.MAX_VALUE);
        emailLabel.getStyleClass().add("label");
        TextField emailField = new TextField();
        emailField.setPromptText("example@email.com");
        VBox emailBox = new VBox(5, emailLabel, emailField);

        // Senhas
        Label senhaLabel = new Label("Senha");
        senhaLabel.setMaxWidth(Double.MAX_VALUE);
        senhaLabel.getStyleClass().add("label");
        PasswordField senhaField = new PasswordField();
        senhaField.setPromptText("********");
        VBox senhaBox = new VBox(5, senhaLabel, senhaField);

        Label senha2Label = new Label("Confirmar senha");
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
		
		btnCadastrar.setOnAction(event -> {
			System.out.println(controller.cadastrar());
		});
		
		// Fazendo o binding bidirecional dos campos com o CadastroController

		nomeField.textProperty().bindBidirectional(controller.nomeProperty);
		documentoField.textProperty().bindBidirectional(controller.documentoProperty);
		emailField.textProperty().bindBidirectional(controller.emailProperty);
		senhaField.textProperty().bindBidirectional(controller.senhaProperty);
		senha2Field.textProperty().bindBidirectional(controller.senha2Property);
		btnVendedor.selectedProperty().bindBidirectional(controller.isVendedorProperty);
		
        // Montando o Card
        card.getChildren().addAll(
            titleLabel, 
            new VBox(5, tipoContaLabel, tipoContaBox),
            nomeBox, 
            cepBox,       
            ruaNumBox,   
            emailBox, 
            senhaBox, 
            senha2Box, 
            btnCadastrar
        );
         StackPane centerPane = new StackPane();
        centerPane.setAlignment(Pos.CENTER); 
        centerPane.getChildren().add(card);
        root.getChildren().add(centerPane);

        Scene scene = new Scene(root, 1000, 700);
            scene.getStylesheets().add(CadastroBoundary.class.getResource("/css/cadastro.css").toExternalForm());

        return scene;
    }
}