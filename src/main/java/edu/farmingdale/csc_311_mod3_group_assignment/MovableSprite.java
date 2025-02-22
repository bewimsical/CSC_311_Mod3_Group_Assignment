package edu.farmingdale.csc_311_mod3_group_assignment;


import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;

//this class is for the robot it handles the robots movement and applies the image to the image view.
public class MovableSprite extends Sprite{
    protected Sprite maze; // this is the maze that the sprite is trying to complete
    protected Image maze_im; // this is the maze image
    protected double pixelRatio; // the pixel reader works with the full sized image. We need to scale the robots coordinates to get correct readings.
    protected PixelReader pr; // used in the collision detection method. Checks pixels at given coordinates.


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
    private boolean isCollision(double x, double y){
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
//This was almost identical to the other is collision method.
//    private boolean isCollisionPoint(double x, double y) {
//        //Uses the maze bounds from the maze sprite
//        double maxX = maze.bounds.getMaxX();
//        double maxY = maze.bounds.getMaxY();
//
//        if (x <= 0 || x >= maxX || y <= 0 || y >= maxY) {
//
//            return false;//outofbounds /=  clear
//        } else {
//
//            //scales coordinates to the full img
//            double checkX = x * pixelRatio;
//            double checkY = y * pixelRatio;
//            Color pixelColor = pr.getColor((int) checkX, (int) checkY);
//            Color white = Color.WHITE;
//            return pixelColor.equals(white);
//        }
//    }

}
