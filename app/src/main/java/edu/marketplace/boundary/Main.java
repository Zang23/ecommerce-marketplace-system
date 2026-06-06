package edu.marketplace.boundary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public String getGreeting() {
        return "Welcome to the E-commerce Marketplace System!";
    }

    @Override
    public void start(Stage primaryStage) {
        LoginBoundary loginBoundary = new LoginBoundary(primaryStage);
        Scene scene = new Scene(loginBoundary.getRoot(), 1000, 600);

        primaryStage.setTitle(getGreeting());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



