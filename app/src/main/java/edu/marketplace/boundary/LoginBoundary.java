package edu.marketplace.boundary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class LoginBoundary {

    private final StackPane root = new StackPane();

    public LoginBoundary(Stage stage) {
        GridPane gridPane = new GridPane();
        root.setStyle("-fx-background-color: #2b2b2b;");

        VBox loginCard = new VBox(20);
        loginCard.getStyleClass().add("login-card");
        loginCard.setMaxSize(380, 420);
        loginCard.setAlignment(Pos.TOP_CENTER);
        loginCard.setPadding(new Insets(40));
        gridPane.add(loginCard, 0, 0);

        Label titleLabel = new Label("Seja bem-vindo!");
        titleLabel.getStyleClass().add("title");
        VBox.setMargin(titleLabel, new Insets(0, 0, 10, 0));
    }

    public Parent getRoot() {
        return root;
    }
}
