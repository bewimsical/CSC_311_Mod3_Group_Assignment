package edu.farmingdale.csc_311_mod3_group_assignment;


import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;

import java.util.*;


//this class is for the robot it handles the robots movement and applies the image to the image view.
public class MovableSprite extends Sprite{
    protected Maze maze; // this is the maze that the sprite is trying to complete
    protected Image maze_im; // this is the maze image
    protected double pixelRatio; // the pixel reader works with the full sized image. We need to scale the robots coordinates to get correct readings.
    protected PixelReader pr; // used in the collision detection method. Checks pixels at given coordinates.

    public MovableSprite(){
        super();
    }
    public MovableSprite(Image image, ImageView sprite,Maze maze){
        super(image,sprite);
        this.maze = maze;
        this.maze_im = maze.getImage();
        this.pixelRatio = maze_im.getHeight()/maze.getSprite().getFitHeight();
        this.pr = maze_im.getPixelReader();
    }


    public Maze getMaze(){
        return this.maze;
    }
    //All move methods are called on keypress in the controller.
    //These methods check if there will be a collision. If there is no collision, the location of the sprite is changed.
    public void moveLeft(){
        double newX = x-5;
        if(!isCollision(newX, y)){
           x = newX;
           sprite.setLayoutX(x);
        }
    }
    public void moveRight(){
        double newX = x+5;
        double width = sprite.getFitWidth();
        if(!isCollision(newX + width, y)){
            x = newX;
            sprite.setLayoutX(x);
            System.out.println(x);
            System.out.println(y);
        }
    }
    public void moveUp(){
        double newY = y-5;
        if(!isCollision(x, newY)){
            y = newY;
            sprite.setLayoutY(y);
        }
    }
    public void moveDown(){
        double newY = y+5;
        double height = sprite.getFitHeight();
        if(!isCollision(x, newY + height)){
            y = newY;
            sprite.setLayoutY(y);
        }
    }

    //this checks if the new coordinates will be out of bounds and returns true if they are.
    //Then this converts the x and y to match the image and checks if pixel color at the given coordinates is white.
    // It returns false if its white and true if it is ant other color.
    protected boolean isCollision(double x, double y){
        double maxX = maze.bounds.getMaxX();
        double maxY = maze.bounds.getMaxY();
        if (x <= 0 || x >= maxX || y <= 0 || y >= maxY){
            return true;
        }else {
            x = x * pixelRatio;
            y = y * pixelRatio;
            Color pixelColor = pr.getColor((int) x, (int) y); // get pixel color
            Color c = new Color(1, 1, 1, 1); // creates a white color object. Could extract to maze background attribute if we make a maze class.
            return (!pixelColor.equals(c));

        }
    }


    public List<Point2D> bfsSolveMaze(double goalX,double goalY,int step){
        Queue<Point2D> frontier = new LinkedList<>();
        Map<Point2D, Point2D> cameFrom = new HashMap<>();

        Point2D start = new Point2D(this.getX(),this.getY());
        frontier.add(start);
        cameFrom.put(start,null);
        Point2D[] directions =new Point2D[]{
                new Point2D(step, 0),
                new Point2D(-step,0),
                new Point2D(0,step),
                new Point2D(0, -step)
        };

        Point2D endNode =null;
        while (!frontier.isEmpty()){
            Point2D current = frontier.poll();
            if (current.distance(goalX,goalY)< step){
                endNode = current;
                break;
            }
            for (Point2D dir : directions){
                Point2D next = current.add(dir);
                double nx = Math.round(next.getX()/step) *step;
                double ny = Math.round(next.getY() /step) *step;
                Point2D nextPoint = new Point2D(nx, ny);

                if (cameFrom.containsKey(nextPoint))
                    continue;
                double maxX = this.maze.bounds.getMaxX();
                double maxY = this.maze.bounds.getMaxY();
                if (nx < 0 ||nx>maxX||ny<0||ny>maxY)
                    continue;
                if (!this.isAreaClear(nx,ny))
                    continue;
                frontier.add(nextPoint);
                cameFrom.put(nextPoint,current);
            }
        }
        if (endNode ==null){
            return new ArrayList<>();
        }
        List<Point2D> path = new LinkedList<>();
        Point2D current =endNode;
        while (current != null){
            path.add(0,current);
            current = cameFrom.get(current);
        }
        return path;
    }

    public void animateAlongPath(List<Point2D> path){
        final int[] index = {0};
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long now){
                if (index[0] >= path.size()){
                    stop();
                    System.out.println("All done!");
                    return;
                }
                Point2D target = path.get(index[0]);
                double targetX = target.getX();
                double targetY = target.getY();
                double currentX = getX();
                double currentY = getY();
                double deltaX = targetX - currentX;
                double deltaY = targetY - currentY;
                double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                double speed = 2;
                if (distance<speed){
                    setX(targetX);
                    setY(targetY);
                    index[0]++;
                } else{
                    double moveX =(deltaX/ distance)* speed;
                    double moveY = (deltaY /distance) * speed;
                    setX(currentX +moveX);
                    setY(currentY+ moveY);
                }
                render();
            }
        };
        timer.start();
    }

    public void solveMaze(double goalX, double goalY,int step){
        List<Point2D> path =bfsSolveMaze(goalX,goalY, step);
        if (path.isEmpty()){
            System.out.println("No path found :(");
        } else{
            animateAlongPath(path);
            System.out.println("Look at me go!");
        }}

    public boolean isAreaClear(double posX, double posY){
        //Checks several key pointsaround the sprite
        double spriteWidth = sprite.getFitWidth();
        double spriteHeight = sprite.getFitHeight();
        //Used De Morgan's Law to invert the logic
        return !(isCollision(posX, posY) ||
                isCollision(posX + spriteWidth, posY) ||
                isCollision(posX, posY + spriteHeight) ||
                isCollision(posX + spriteWidth, posY + spriteHeight));

    }
}
