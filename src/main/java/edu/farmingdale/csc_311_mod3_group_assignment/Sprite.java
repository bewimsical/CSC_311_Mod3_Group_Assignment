package edu.farmingdale.csc_311_mod3_group_assignment;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
    protected double x;
    protected double y;
    protected Image image;
    @FXML
    protected ImageView sprite;

    public Sprite(){
        this.x = 0;
        this.y = 0;
        this.image = null;
        this.sprite = null;
    }

    public Sprite(double x, double y, Image image, ImageView sprite){
        this.x = x;
        this.y = y;
        this.image = image;
        this.sprite = sprite;
        render();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }

    //TODO fix this
    public void render(){
        this.sprite.setImage(this.image);
    }


}
