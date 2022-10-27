package com.nikhilaukhaj.tankerman;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    private int score ;
    Timer timer1;
    Timer timer2;


    @FXML
    private Pane imagepane;

    @FXML
    private AnchorPane level1_pane;

    @FXML
    private ImageView img_tankerman;

    @FXML
    private Label txtScore;

    @FXML
    private ImageView enemy1,enemy2,enemy3,enemy4,enemy5,enemy6;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerDirection = 'L';
        shooting = false;
        score = 0;
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
                        changeWarriorDirection("images/warrior_up.png",img_tankerman.getX(),img_tankerman.getY()-10,'U');
                        break;
                    case RIGHT:
                        changeWarriorDirection("images/warrior_right.png",img_tankerman.getX()+10,img_tankerman.getY(),'R');
                        break;
                    case DOWN:
                        changeWarriorDirection("images/warrior_down.png",img_tankerman.getX(),img_tankerman.getY()+10,'D');
                        break;
                    case LEFT:
                        changeWarriorDirection("images/warrior_left.png",img_tankerman.getX()-10,img_tankerman.getY(),'L');

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

    private void changeWarriorDirection(String imagePath,double x,double y,char direction){
        setWarriorImage(imagePath);
        img_tankerman.setX(x);
        img_tankerman.setY(y);
        playerDirection = direction;
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
        imageView.setFitWidth(10);
        imageView.setFitWidth(10);
        imageView.setX((img_tankerman.getBoundsInParent().getMinX() + img_tankerman.getBoundsInParent().getCenterX())/2);
//        imageView.setX(1351);
        imageView.setY((img_tankerman.getBoundsInParent().getMinY() + img_tankerman.getBoundsInParent().getCenterY())/2);
        level1_pane.getChildren().add(imageView);
        System.out.println ("SHOOTING");

        char shootingDirection = playerDirection;

        switch (shootingDirection) {
            case 'U' -> changeBulletImage(imageView, "assets/bullets/light_shell_up_x.png");
            case 'R' -> changeBulletImage(imageView, "assets/bullets/light_shell_right_x.png");
            case 'D' -> changeBulletImage(imageView, "assets/bullets/light_shell_down_x.png");
            case 'L' -> changeBulletImage(imageView, "assets/bullets/light_shell_left_x.png");
        }
        //start timer
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                switch (shootingDirection) {
                    case 'U' -> imageView.setY(imageView.getY() - 5);
                    case 'R' -> imageView.setX(imageView.getX() + 5);
                    case 'D' -> imageView.setY(imageView.getY() + 5);
                    case 'L' -> imageView.setX(imageView.getX() - 5);
                }


                //check if bullet is outside border
                if(imageView.getBoundsInParent().getCenterX() < 0 || imageView.getBoundsInParent().getMinX() > level1_pane.getWidth() || imageView.getBoundsInParent().getCenterY() < 0 || imageView.getBoundsInParent().getCenterY() > level1_pane.getHeight()){
                    imageView.setVisible(false);
                    timer1.cancel();
                    shooting = false;
                }

                //check Bullet collision with enemy
                checkPlayerBulletAndEnemyCollision(imageView,enemy1);
                checkPlayerBulletAndEnemyCollision(imageView,enemy2);
                checkPlayerBulletAndEnemyCollision(imageView,enemy3);
                checkPlayerBulletAndEnemyCollision(imageView,enemy4);
                checkPlayerBulletAndEnemyCollision(imageView,enemy5);
                checkPlayerBulletAndEnemyCollision(imageView,enemy6);
            }
        };
        timer1 = new Timer();
        timer1.scheduleAtFixedRate(task, 10, 10);

    }

    private boolean checkCollision(ImageView imgView1,ImageView imgView2){
        if(imgView1.intersects(imgView2.getBoundsInParent())){
            return true;
        }
        return false;
    }

    private void checkPlayerBulletAndEnemyCollision(ImageView imgView1,ImageView imgView2){
        if(checkCollision(imgView1,imgView2)){
            if(imgView2.isVisible()){
                System.out.println("Enemy shot dead");
                score++;
                imgView2.setVisible(false);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        level1_pane.getChildren().remove(imgView2);
                        txtScore.setText("Score: " + score);
                    }
                });
            }
        }
    }

    private void changeBulletImage(ImageView img,String imagePath){
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        img.setImage(image);
    }


}
