package edu.marketplace.boundary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    


    @Override
    public void start(Stage stage) throws Exception {

        LoginBoundary telaLogin = new LoginBoundary(stage);

        Scene scene = new Scene(telaLogin.getRoot(), 900,600);

        stage.setScene(scene);
        stage.setTitle("MarketPlace");
        stage.show();

    }

    public static void main(String[] args) {
        System.out.println("blablalba");
        launch(args);
    }

}
