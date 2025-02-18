package edu.farmingdale.csc_311_mod3_group_assignment;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MovableSprite extends Sprite{

    public MovableSprite(){
        super();
    }
    public MovableSprite(double x, double y, Image image, ImageView sprite){
        super(x,y,image,sprite);
    }

    public void moveLeft(){
        x = x-5;
        sprite.setLayoutX(x);
    }
    public void moveRight(){
        x = x+5;
        sprite.setLayoutX(x);
    }
    public void moveUp(){
        y = y - 5;
        sprite.setLayoutY(y);
    }
    public void moveDown(){
        y = y +5;
        sprite.setLayoutY(y);
    }


}
