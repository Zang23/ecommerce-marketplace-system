package edu.marketplace.boundary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public String getGreeting() {
        return "Welcome to the E-commerce Marketplace System!";
    }

    @Override
    public void start(Stage stage) {
       Scene scene = LoginBoundary.criarCena(stage);    
        //Testando as outras telas:
        //Scene scene = CartaoBoundary.criarCena(stage); 
        stage.setScene(scene);
        stage.setTitle(getGreeting());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



