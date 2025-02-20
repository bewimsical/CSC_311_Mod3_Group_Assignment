package edu.farmingdale.csc_311_mod3_group_assignment;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class MazeController {
    @FXML
    private Button restartMaze1;

    @FXML
    private Button restartMaze2;

    @FXML
    private Button solveMaze1;

    @FXML
    private Button solveMaze2;

    @FXML
    private RadioButton carButton1;

    @FXML
    private RadioButton carButton2;

    @FXML
    private RadioButton robotButton1;

    @FXML
    private RadioButton robotButton2;

//    @FXML
//    private AnchorPane maze1;

    @FXML
    private AnchorPane maze2;

    @FXML
    private ImageView sprite1;
    @FXML
    private ImageView maze1;

    private Image robot = new Image(MazeApplication.class.getResource("images/robot.png").toExternalForm());
    private Image maze1_im = new Image(MazeApplication.class.getResource("images/maze.png").toExternalForm());

    private MovableSprite currentSprite;
    private Sprite currentMaze;


    private AnimationTimer timer;

    /**
     * This method runs when the controller is loaded
     */
    @FXML
    public void initialize(){
        System.out.println("STARTING APPLICATION");
        robotButton1.setSelected(true);
        currentMaze = new Sprite(maze1_im, maze1);
        currentSprite = new MovableSprite(robot, sprite1, currentMaze);
    }
    @FXML
    void restart() {
        System.out.println("button clicked");
    }

    @FXML
    void setSpriteMaze1(ActionEvent e) {
        if (e.getSource().equals(robotButton1)){
            carButton1.setSelected(false);
        }else if (e.getSource().equals(carButton1)){
            robotButton1.setSelected(false);
        }
    }
//
//    // TODO: apply towards robot and sprite
//    @FXML
//    public void BarrierPathFinder(ActionEvent e){
//        Image maze = new Image(MazeApplication.class.getResource("images/maze.png").toExternalForm());
//        timer = new AnimationTimer(){
//            @Override
//            public void handle(long now) {
//                current.render();
//                double dx = current.getX();
//                double dy = current.getY();
//                if (dx == 300 && dy == 300){
//                    timer.stop();
//                }
//                Bounds mazeBounds = mazeImage.getBoundsInParent();
//                Bounds objectBounds = movingObject.getBoundsInParent();
//
//                // If Object hit walls
//                if(objectBounds.intersects(mazeBounds)){
//                    System.out.println("object intersects maze");
//                    dx = -dx;
//                    dy = -dy;
//                }
//                // Keep the object within bounds(optional)
//                if(dx < 0 || dx > mazeBounds.getWidth() || dy < 0 || dy > mazeBounds.getHeight()){
//                    dx = -dx;
//                    dy = -dy;
//                }
//            }
//        };
//        timer.start();
//
////    to check if an object hit a barrier in javaFX
//
//
//    }


    @FXML
    void handleKey(KeyEvent e){
        switch (e.getCode()){
            case KeyCode.W:
                System.out.println("w pressed");
                currentSprite.moveUp();
                break;
            case KeyCode.A:
                System.out.println("a pressed");
                currentSprite.moveLeft();
                break;
            case KeyCode.S:
                System.out.println("s pressed");
                currentSprite.moveDown();
                break;
            case KeyCode.D:
                System.out.println("d pressed");
                currentSprite.moveRight();
                break;
        }
    }

}