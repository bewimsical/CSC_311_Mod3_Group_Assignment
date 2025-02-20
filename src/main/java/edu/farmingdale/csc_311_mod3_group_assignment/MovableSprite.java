package edu.farmingdale.csc_311_mod3_group_assignment;


import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;

public class MovableSprite extends Sprite{
    protected Sprite maze;
    protected Image maze_im;
    protected double pixelRatio;
    protected PixelReader pr;


    public MovableSprite(){
        super();
    }
    public MovableSprite(Image image, ImageView sprite, Sprite maze){
        super(image,sprite);
        this.maze = maze;
        this.maze_im = maze.getImage();
        this.pixelRatio = maze_im.getHeight()/maze.getSprite().getFitHeight();
        this.pr = maze_im.getPixelReader();
    }

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
    public boolean isCollision(double x, double y){
        double maxX = maze.bounds.getMaxX();
        double maxY = maze.bounds.getMaxY();
        if (x <= 0 || x >= maxX || y <= 0 || y >= maxY){
            return true;
        }else {
            x = x * pixelRatio;
            y = y * pixelRatio;
            Color pixelColor = pr.getColor((int) x, (int) y);
            Color c = new Color(1, 1, 1, 1);

            return (!pixelColor.equals(c));
        }
    }
}
