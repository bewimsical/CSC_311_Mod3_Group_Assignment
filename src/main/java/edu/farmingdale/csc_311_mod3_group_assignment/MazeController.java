package edu.farmingdale.csc_311_mod3_group_assignment;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.List;

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


    @FXML
    private ImageView sprite1;
    @FXML
    private ImageView maze1;


    private Image robot = new Image(MazeApplication.class.getResource("images/robot.png").toExternalForm());
    private Image maze1_im = new Image(MazeApplication.class.getResource("images/maze.png").toExternalForm());
    // data members for the current maze and moveable object. Will change when tab changes and radio buttons are pressed
    private MovableSprite currentSprite;
    private Sprite currentMaze;

    //we don't need this movement is handled in the movable sprite class
//    private AnimationTimer timer;

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
    //sets the x and y values to the start of the first maze. If we a maze class then we could set it to the current maze start values
    @FXML
    void restart() {
        System.out.println("TEST!!");
        currentSprite.setX(25);
        currentSprite.setY(242);
    }

    //toggles the radio buttons.
    // TODO create new movable sprite instances of the robot and car
    @FXML
    void setSpriteMaze1(ActionEvent e) {
        if (e.getSource().equals(robotButton1)){
            carButton1.setSelected(false); //
        }else if (e.getSource().equals(carButton1)){
            robotButton1.setSelected(false);
        }
    }
    @FXML
    void setSpriteMaze2(ActionEvent e) {
        if (e.getSource().equals(robotButton2)){
            carButton2.setSelected(false);
        }else if (e.getSource().equals(carButton2)){
            robotButton2.setSelected(false);
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
public void mazeSolver1 (MouseEvent event) {
    List<Point2D> path = List.of(
            new Point2D(22.0, 236.0),
            new Point2D(47.0, 236.0),
            new Point2D(47.0, 136.0),
            new Point2D(252.0, 136.0),
            new Point2D(252.0, 86.0),
            new Point2D(302.0, 86.0),
            new Point2D(302.0, 291.0),
            new Point2D(357.0, 291.0),
            new Point2D(357.0, 191.0),
            new Point2D(462.0, 191.0),
            new Point2D(462.0, 91.0),
            new Point2D(512.0, 91.0),
            new Point2D(512.0, 221.0),
            new Point2D(537.0, 221.0)
    );
    animateSpriteAlongPath(path);
}


    private void animateSpriteAlongPath(List<Point2D> path){
        final int[] index = {0};

        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long now){
                if (index[0] >= path.size()){
                    stop();
                    return;
                }
                Point2D target = path.get(index[0]);
                double targetX = target.getX();
                double targetY = target.getY();
                double currentX = currentSprite.getX(); // Use currentSprite here
                double currentY = currentSprite.getY();
                double deltaX = targetX - currentX;
                double deltaY = targetY - currentY;
                double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                double speed = 2;

                if (distance < speed){
                    currentSprite.setX(targetX);
                    currentSprite.setY(targetY);
                    currentSprite.getSprite().setLayoutX(targetX);
                    currentSprite.getSprite().setLayoutY(targetY);
                    index[0]++;
                } else {
                    double moveX = (deltaX / distance) * speed;
                    double moveY = (deltaY / distance) * speed;
                    currentSprite.setX(currentX + moveX);
                    currentSprite.setY(currentY + moveY);
                    currentSprite.getSprite().setLayoutX(currentSprite.getX());
                    currentSprite.getSprite().setLayoutY(currentSprite.getY());
                }
                currentSprite.render();
            }
        };
        timer.start();
    }


    //Handles the key events. Calls the move methods in the movable sprite class
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