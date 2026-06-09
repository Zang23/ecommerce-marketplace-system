package edu.marketplace.boundary;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class LoginBoundary extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = criarCena(stage);
        stage.setTitle("Tela de Login");
        stage.setScene(scene);
        stage.show();
    }

    public static Scene criarCena(Stage stage) {
        // Container principal (fundo cinza escuro)
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #2b2b2b;");

        // O "Card" escuro do formulário
        VBox loginCard = new VBox(20);
        loginCard.getStyleClass().add("login-card");
        loginCard.setMaxSize(380, 420);
        loginCard.setAlignment(Pos.TOP_CENTER);
        loginCard.setPadding(new Insets(40));

       
        Label titleLabel = new Label("Seja bem-vindo!");
        titleLabel.getStyleClass().add("title");
        VBox.setMargin(titleLabel, new Insets(0, 0, 10, 0)); 

        
        Label emailLabel = new Label("E-mail");
        emailLabel.setMaxWidth(Double.MAX_VALUE);
        TextField emailField = new TextField();
        emailField.setPromptText("example@email.com");
        VBox emailBox = new VBox(5, emailLabel, emailField);

        
        Label senhaLabel = new Label("Senha");
        senhaLabel.setMaxWidth(Double.MAX_VALUE);
        PasswordField senhaField = new PasswordField();
        senhaField.setPromptText("********");
        VBox senhaBox = new VBox(5, senhaLabel, senhaField);

        
        Button btnEntrar = new Button("Entrar");
        btnEntrar.setMaxWidth(Double.MAX_VALUE); 
        btnEntrar.getStyleClass().add("btn-entrar");
        VBox.setMargin(btnEntrar, new Insets(15, 0, 10, 0));

        
        Text textoNormal = new Text("Não tem uma conta? ");
        textoNormal.setFill(Color.web("#888888"));
        
        Text textoLink = new Text("criar conta");
        textoLink.setFill(Color.web("#4a6fe3"));
        textoLink.getStyleClass().add("link-text");

        // Adicionando o evento de clique no texto azul
        textoLink.setOnMouseClicked(event -> {
            // Pede a tela de cadastro montada
            Scene cenaCadastro = CadastroBoundary.criarCena(stage);
            
            // Troca a cena atual da janela (Stage) para a nova cena de cadastro
            stage.setScene(cenaCadastro);
            stage.setTitle("Criar Conta");
        });

        TextFlow linkFlow = new TextFlow(textoNormal, textoLink);
        linkFlow.setTextAlignment(TextAlignment.CENTER);

        
        loginCard.getChildren().addAll(titleLabel, emailBox, senhaBox, btnEntrar, linkFlow);

    
        root.getChildren().add(loginCard);

        
        Scene scene = new Scene(root, 1000, 700);
        
        //Conectando o arquivo CSS correto do projeto.
        scene.getStylesheets().add(LoginBoundary.class.getResource("/css/login.css").toExternalForm());

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
