package edu.farmingdale.csc_311_mod3_group_assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    private AnchorPane maze1;

    @FXML
    private AnchorPane maze2;

    @FXML
    private ImageView sprite1;

    private Image robot = new Image(MazeApplication.class.getResource("images/robot.png").toExternalForm());

    /**
     * This method runs when the controller is loaded
     */
    @FXML
    public void initialize(){
        System.out.println("STARTING APPLICATION");
        robotButton1.setSelected(true);
        MovableSprite robotSprite = new MovableSprite(sprite1.getLayoutX(), sprite1.getLayoutY(), robot, sprite1);
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

    void handleKey(KeyEvent e){
        switch (e.getCode()){
            case KeyCode.W:
                System.out.println("w pressed");
                break;
            case KeyCode.A:
                System.out.println("a pressed");
                break;
            case KeyCode.S:
                System.out.println("s pressed");
                break;
            case KeyCode.D:
                System.out.println("d pressed");
                break;
        }
    }

}