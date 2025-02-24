package edu.farmingdale.csc_311_mod3_group_assignment;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.*;

public class MazeController {

    @FXML
    private Button solveMaze1;

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

    @FXML
    private ImageView maze2;

    @FXML
    private ImageView sprite2;

    @FXML
    private Group car1;

    @FXML
    private Group car2;

    private Image robot = new Image(MazeApplication.class.getResource("images/robot.png").toExternalForm());
    private Image maze1_im = new Image(MazeApplication.class.getResource("images/maze.png").toExternalForm());
    private Image maze2_im = new Image(MazeApplication.class.getResource("images/maze2.png").toExternalForm());

    // data members for the current maze and moveable object. Will change when tab changes and radio buttons are pressed
    private MovableSprite currentSprite;
    private Maze currentMaze;

    // This method runs when the controller is loaded
    @FXML
    public void initialize(){
        System.out.println("Starting Application");
        System.out.println("Hello Mr.Roboto!");
        robotButton1.setSelected(true);
        carButton1.setSelected(false);
        currentMaze = new Maze(maze1_im, maze1,25 ,242,537,221);
        currentSprite = new MovableSprite(robot, sprite1, currentMaze);
        currentSprite.setX(currentSprite.getMaze().getStartX());
        currentSprite.setY(currentSprite.getMaze().getStartY());
    }
    //Methods for switching the tabs
    @FXML
    public void initialize1(){
        robotButton1.setSelected(true);
        carButton1.setSelected(false);
        currentMaze = new Maze(maze1_im, maze1, 25 ,242,537,221);
        currentSprite = new MovableSprite(robot, sprite1, currentMaze);
        currentSprite.setX(currentSprite.getMaze().getStartX());
        currentSprite.setY(currentSprite.getMaze().getStartY());
    }
    @FXML
    public void initialize2() {
        System.out.println("Switching Maze");
        robotButton2.setSelected(true);
        carButton2.setSelected(false);
        currentMaze = new Maze(maze2_im, maze2, 30 ,60,490,330);
        currentSprite = new MovableSprite(robot, sprite2, currentMaze);
        currentSprite.setX(currentSprite.getMaze().getStartX());
        currentSprite.setY(currentSprite.getMaze().getStartY());
    }

    //moves sprite to beginning of maze
    @FXML
    void setrestartMaze1(ActionEvent e) {
        System.out.println("Returning To Start Position");
        currentSprite.setX(currentSprite.getMaze().getStartX());
        currentSprite.setY(currentSprite.getMaze().getStartY());
        solveMaze1.setDisable(false);
    }

    //methods to toggle the radio buttons and the car and robot.
    @FXML
    void setSpriteMaze1(ActionEvent e) {
        if (e.getSource().equals(robotButton1)){
            robotButton1.setSelected(true);
            currentMaze = new Maze(maze1_im, maze1, 25 ,242,537,221);
            currentSprite = new MovableSprite(robot, sprite1, currentMaze);
            sprite1.setVisible(true);
            car1.setVisible(false);
            carButton1.setSelected(false); //
            currentSprite.setX(currentSprite.getMaze().getStartX());
            currentSprite.setY(currentSprite.getMaze().getStartY());
            System.out.println("Arigato Mr.Roboto!");
        }else if (e.getSource().equals(carButton1)){
            carButton1.setSelected(true);
//            currentSprite = new MovableSprite(car, sprite1, currentMaze);
            currentSprite = new Car(car1, currentMaze);
            sprite1.setVisible(false);
            car1.setVisible(true);
            robotButton1.setSelected(false);
            currentSprite.setX(currentSprite.getMaze().getStartX());
            currentSprite.setY(currentSprite.getMaze().getStartY());
            System.out.println("VROOM VROOM!");
        }
    }
    @FXML
    void setSpriteMaze2(ActionEvent e) {
        if (e.getSource().equals(robotButton2)){
            robotButton2.setSelected(true);
            currentMaze = new Maze(maze2_im, maze2, 30 ,60,490,330);
            currentSprite = new MovableSprite(robot, sprite2, currentMaze);
            sprite2.setVisible(true);
            car2.setVisible(false);
            carButton2.setSelected(false);
            currentSprite.setX(currentSprite.getMaze().getStartX());
            currentSprite.setY(currentSprite.getMaze().getStartY());
            System.out.println("Mr.Roboto Part 2!?");
        }else if (e.getSource().equals(carButton2)){
            carButton2.setSelected(true);
            //currentSprite = new MovableSprite(car, sprite2, currentMaze);
            currentSprite = new Car(car2, currentMaze);
            sprite2.setVisible(false);
            car2.setVisible(true);
            robotButton2.setSelected(false);
            currentSprite.setX(currentSprite.getMaze().getStartX());
            currentSprite.setY(currentSprite.getMaze().getStartY());
            System.out.println("VROOM VROOM!");
        }
    }
    //method to solve maze
    @FXML
    public void mazeSolver1(MouseEvent event) {
        double goalX = currentSprite.getMaze().getGoalX();
        double goalY = currentSprite.getMaze().getGoalY();
        int step =5;
        currentSprite.solveMaze(goalX,goalY,step);
    }

    //Handles the key events. Calls the move methods in the movable sprite class
    @FXML
    void handleKey(KeyEvent e){
        switch (e.getCode()){
            case KeyCode.W:
                System.out.println("Going up");
                currentSprite.moveUp();
                break;
            case KeyCode.A:
                System.out.println("Moving left");
                currentSprite.moveLeft();
                break;
            case KeyCode.S:
                System.out.println("Going down");
                currentSprite.moveDown();
                break;
            case KeyCode.D:
                System.out.println("Moving right");
                currentSprite.moveRight();
                break;
        }
    }
}