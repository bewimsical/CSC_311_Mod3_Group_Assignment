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
}
