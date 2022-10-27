package com.nikhilaukhaj.tankerman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


//        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Level1.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setTitle("Level 1");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Level1Controller controller = loader.getController();
        controller.setStage(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
