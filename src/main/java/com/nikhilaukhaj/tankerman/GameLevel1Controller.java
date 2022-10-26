package com.nikhilaukhaj.tankerman;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameLevel1Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private char playerDirection;

    @FXML
    private AnchorPane anchorPane1;

    @FXML
    private ImageView img_tankerman;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setStage(Stage stage){
      this.stage = stage;
      Scene scene1 = stage.getScene();
      scene1.setOnKeyPressed(new EventHandler<KeyEvent>() {
          @Override
          public void handle(KeyEvent keyEvent) {
              switch (keyEvent.getCode()){
                  case W:
                      //warrior up
                      setWarriorImage("images/warrior_up.png");
                      img_tankerman.setY(img_tankerman.getY() - 10);
                      playerDirection = 'U';
                      break;
                  case D:
                      //warrior right
                      setWarriorImage("images/warrior_right.png");
                      img_tankerman.setX(img_tankerman.getX() + 10);
                      playerDirection = 'R';
                      break;
                  case S:
                      //warrior down
                      setWarriorImage("images/warrior_down.png");
                      img_tankerman.setY(img_tankerman.getY() + 10);
                      playerDirection = 'D';

                      break;
                  case A:
                      //warrior left
                      setWarriorImage("images/warrior_left.png");
                      img_tankerman.setX(img_tankerman.getX() - 10);
                      playerDirection = 'L';

                      break;
                  case SPACE:

                      break;
                  default:
                      System.out.println("Key pressed is " + keyEvent.getCode());
              }
          }
      });
    }

    private void setWarriorImage(String path){
        Image image = new Image(getClass().getResourceAsStream(path));
        img_tankerman.setImage(image);
    }

    private void shoot(){
        Image image = new Image(getClass().getResourceAsStream("images/assets/light_shell.png"));

    }
}
