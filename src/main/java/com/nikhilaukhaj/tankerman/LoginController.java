package com.nikhilaukhaj.tankerman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    TextField label_user;

    @FXML
    TextField label_pass;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void checkLogin(ActionEvent e) throws IOException {
        if(checkLogin(label_user.getText(),label_pass.getText())){
            //redirect to menu scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            root = loader.load();
            MenuController menuController = loader.getController();

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            //add css
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);
            //setting scene and stage
            stage.setScene(scene);
            stage.setTitle("Menu");
            stage.show();
            System.out.println("Login successful, showing menu");
        }else{
            System.out.println("Login unsuccessful :(");
            //display error message
        }
    }

    public boolean checkLogin(String username,String pass){
        return username.equals("nikhil") && pass.equals("1234");
    }
}
