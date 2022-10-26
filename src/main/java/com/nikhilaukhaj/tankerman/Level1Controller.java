package com.nikhilaukhaj.tankerman;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Level1Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private char playerDirection;
    private boolean shooting;

    @FXML
    private Pane imagepane;

    @FXML
    private AnchorPane level1_pane;

    @FXML
    private ImageView img_tankerman;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerDirection = 'L';
        shooting = false;
    }

    public void setStage(Stage stage){
        this.stage = stage;
        this.scene = stage.getScene();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case UP:
                        //warrior up
                        setWarriorImage("images/warrior_up.png");
                        img_tankerman.setY(img_tankerman.getY() - 10);
                        playerDirection = 'U';
                        System.out.println("y:" + img_tankerman.getBoundsInParent().getCenterY());

                        break;
                    case RIGHT:
                        //warrior right
                        setWarriorImage("images/warrior_right.png");
                        img_tankerman.setX(img_tankerman.getX() + 10);
                        playerDirection = 'R';
                        System.out.println("x:" + img_tankerman.getBoundsInParent().getCenterX());

                        break;
                    case DOWN:
                        //warrior down
                        setWarriorImage("images/warrior_down.png");
                        img_tankerman.setY(img_tankerman.getY() + 10);
                        playerDirection = 'D';
                        System.out.println(img_tankerman.getY());
                        System.out.println("y:" + img_tankerman.getBoundsInParent().getCenterY());

                        break;
                    case LEFT:
                        //warrior left
                        setWarriorImage("images/warrior_left.png");
                        img_tankerman.setX(img_tankerman.getX() - 10);
                        playerDirection = 'L';
                        System.out.println("x:" + img_tankerman.getBoundsInParent().getCenterX());


                        break;
                    case SPACE:
                        //shoot bullet
                        if(!shooting){
                            shoot();
                        }
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
        shooting = true;
        Image image = new Image(getClass().getResourceAsStream("images/assets/light_shell.png"));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setFitWidth(100);
        imageView.setX((img_tankerman.getBoundsInParent().getMinX() + img_tankerman.getBoundsInParent().getCenterX())/2);
//        imageView.setX(1351);
        imageView.setY((img_tankerman.getBoundsInParent().getMinY() + img_tankerman.getBoundsInParent().getCenterY())/2);
        level1_pane.getChildren().add(imageView);
        System.out.println ("SHOOTING");

        //start timer
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                switch (playerDirection){
                    case 'U':
                        imageView.setY(imageView.getY()-5);
                        break;
                    case 'R':
                        imageView.setX(imageView.getX()+5);
                        break;
                    case 'D':
                        imageView.setY(imageView.getY()+5);
                        break;
                    case 'L':
                        imageView.setX(imageView.getX()-5);
                        break;
                }
            }
        };
        timer.scheduleAtFixedRate(task,10,10);
    }


}
