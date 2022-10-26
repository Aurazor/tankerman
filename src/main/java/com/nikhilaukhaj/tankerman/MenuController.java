package com.nikhilaukhaj.tankerman;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void playGame(ActionEvent e) throws IOException {
        //open new view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameLevel1.fxml"));
        root = loader.load();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();



        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Level 1");
        stage.show();

        System.out.println("Game 1 level pass 1");
        GameLevel1Controller controller = loader.getController();
        controller.setStage(stage);

        System.out.println("Playing game at level 1");
    }
}
