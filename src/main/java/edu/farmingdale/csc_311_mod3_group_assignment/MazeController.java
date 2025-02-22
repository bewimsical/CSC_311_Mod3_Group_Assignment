package edu.farmingdale.csc_311_mod3_group_assignment;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
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

    @FXML
    private ImageView maze2;

    @FXML
    private ImageView sprite2;

    @FXML
    private Tab mazeTab1;

    @FXML
    private Tab mazeTab2;


    private boolean isSolving;


    private Image robot = new Image(MazeApplication.class.getResource("images/robot.png").toExternalForm());
    private Image car = new Image(MazeApplication.class.getResource("images/car.png").toExternalForm());
    private Image maze1_im = new Image(MazeApplication.class.getResource("images/maze.png").toExternalForm());
    private Image maze2_im = new Image(MazeApplication.class.getResource("images/maze2.png").toExternalForm());
    // data members for the current maze and moveable object. Will change when tab changes and radio buttons are pressed
    private MovableSprite currentSprite;
    private Maze currentMaze;

    //we don't need this movement is handled in the movable sprite class
//    private AnimationTimer timer;

    /**
     * This method runs when the controller is loaded
     */
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
    //sets the x and y values to the start of the first maze. If we a maze class then we could set it to the current maze start values
    @FXML
    void restart() {

        currentSprite.setX(currentSprite.getMaze().getStartX());
        currentSprite.setY(currentSprite.getMaze().getStartY());
    }
    @FXML
    void setrestartMaze1(ActionEvent e) {
        System.out.println("Returning To Start Position");
        currentSprite.setX(currentSprite.getMaze().getStartX());
        currentSprite.setY(currentSprite.getMaze().getStartY());
        solveMaze1.setDisable(false);
    }



    //toggles the radio buttons.
    // TODO create new movable sprite instances of the robot and car
    @FXML
    void setSpriteMaze1(ActionEvent e) {
        if (e.getSource().equals(robotButton1)){
            robotButton1.setSelected(true);
            currentMaze = new Maze(maze1_im, maze1, 25 ,242,537,221);
            currentSprite = new MovableSprite(robot, sprite1, currentMaze);
            carButton1.setSelected(false); //
            currentSprite.setX(currentSprite.getMaze().getStartX());
            currentSprite.setY(currentSprite.getMaze().getStartY());
            System.out.println("Arigato Mr.Roboto!");
        }else if (e.getSource().equals(carButton1)){
            carButton1.setSelected(true);
            currentSprite = new MovableSprite(car, sprite1, currentMaze);
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
            carButton2.setSelected(false);
            currentSprite.setX(currentSprite.getMaze().getStartX());
            currentSprite.setY(currentSprite.getMaze().getStartY());
            System.out.println("Mr.Roboto Part 2!?");
        }else if (e.getSource().equals(carButton2)){
            carButton2.setSelected(true);
            currentSprite = new MovableSprite(car, sprite2, currentMaze);
            robotButton2.setSelected(false);
            currentSprite.setX(currentSprite.getMaze().getStartX());
            currentSprite.setY(currentSprite.getMaze().getStartY());
            System.out.println("VROOM VROOM!");
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

    private void animateSpriteAlongPath(List<Point2D> path){
        restartMaze1.setDisable(true);
        solveMaze1.setDisable(true);
        restartMaze2.setDisable(true);
        solveMaze2.setDisable(true);
        mazeTab1.setDisable(true);
        mazeTab2.setDisable(true);
        final int[] index = {0};

        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long now){
                if (index[0] >= path.size()){
                    stop();
                    restartMaze1.setDisable(false);
                    restartMaze2.setDisable(false);
                    solveMaze2.setDisable(false);
                    mazeTab1.setDisable(false);
                    mazeTab2.setDisable(false);

                    System.out.println("All done!");
                    return;
                }
                Point2D target =path.get(index[0]);
                double targetX =target.getX();
                double targetY = target.getY();
                double currentX =currentSprite.getX();
                double currentY = currentSprite.getY();
                double deltaX =targetX -currentX;
                double deltaY = targetY- currentY;
                double distance =Math.sqrt(deltaX*deltaX+deltaY*deltaY);
                double speed =2;
                if (distance<speed){
                    currentSprite.setX(targetX); //set x/y already sets the layout
                    currentSprite.setY(targetY);
//                    currentSprite.getSprite().setLayoutX(targetX);
//                    currentSprite.getSprite().setLayoutY(targetY);
                    index[0]++;
                } else{
                    double moveX = (deltaX /distance) *speed;
                    double moveY =(deltaY/ distance)* speed;
                    currentSprite.setX(currentX+ moveX);
                    currentSprite.setY(currentY +moveY);
//                    currentSprite.getSprite().setLayoutX(currentSprite.getX());
//                    currentSprite.getSprite().setLayoutY(currentSprite.getY());
                }
                currentSprite.render();

            }
        };
        timer.start();

    }



    private List<Point2D> bfsSolveMaze(double startX, double startY, double goalX, double goalY, int step){
        Queue<Point2D> frontier = new LinkedList<>();
        Map<Point2D, Point2D> cameFrom = new HashMap<>();

        Point2D start = new Point2D(startX, startY);
        frontier.add(start);
        cameFrom.put(start, null);

        //Basically allows movement in all directions
        Point2D[] directions = new Point2D[]{
                new Point2D(step, 0),
                new Point2D(-step, 0),
                new Point2D(0, step),
                new Point2D(0, -step)
        };
        Point2D endNode = null;
        while (!frontier.isEmpty()){
            Point2D current = frontier.poll();
            if (current.distance(goalX, goalY) < step) {
                endNode = current;
                break;
            }
            for (Point2D dir :directions){
                Point2D next =current.add(dir);
                double nx = Math.round(next.getX()/ step)* step;
                double ny =Math.round(next.getY() /step) *step;
                Point2D nextPoint = new Point2D(nx,ny);

                if(cameFrom.containsKey(nextPoint))
                    continue;
                //Use mazes bounds from currentSprite.maze
                double maxX = currentSprite.maze.bounds.getMaxX();
                double maxY = currentSprite.maze.bounds.getMaxY();
                if (nx < 0 || nx > maxX || ny < 0 || ny > maxY)
                    continue;
                if (!currentSprite.isAreaClear(nx, ny))
                    continue;
                frontier.add(nextPoint);
                cameFrom.put(nextPoint, current);
            }
        }

        if (endNode == null) {
            //System.out.println("No path found :(");
            return new ArrayList<>();
        }

        //Reconstructs the path
        List<Point2D> path = new LinkedList<>();
        Point2D current = endNode;
        while (current != null){
            path.add(0, current);
            current = cameFrom.get(current);
        }
        return path;
    }
    //does not work because end coordinates are out of bounds. We should make a maze class that has startX startY endX and endY values.
    @FXML
    public void mazeSolver1(MouseEvent event){

        double startX = currentSprite.getX();
        double startY = currentSprite.getY();

       double goalX = currentSprite.getMaze().getGoalX();//If we make a maze class we can use the current mazes start and end here.
        double goalY = currentSprite.getMaze().getGoalY();;
        int step = 5;
        List<Point2D> path = bfsSolveMaze(startX,startY,goalX,goalY,step);
        if(path.isEmpty()){
            System.out.println("No path found :(");
        }else{
            animateSpriteAlongPath(path);
            System.out.println("Look at me go!");
        }
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