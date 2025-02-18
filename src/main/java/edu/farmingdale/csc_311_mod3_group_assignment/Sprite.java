package edu.farmingdale.csc_311_mod3_group_assignment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
    protected int x;
    protected int y;
    protected Image image;

    public Sprite(){
        this.x = 0;
        this.y = 0;
        this.image = null;
    }

    public Sprite(int x, int y, Image image){
        this.x = x;
        this.y = y;
        this.image = image;
    }
    //TODO fix this
    public void render(ImageView image){
        image.setImage(this.image);
    }
}
