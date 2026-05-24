package edu.marketplace.boundary;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginBoundary {

    private StackPane root = new StackPane();
    
    public LoginBoundary(Stage stage){

    }

    public Parent getRoot(){
        return root;
    }

}
